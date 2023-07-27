package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.aiyangniu.mall.common.exception.Asserts;
import com.aiyangniu.mall.enter.mapper.*;
import com.aiyangniu.mall.enter.model.bo.CartPromotionItem;
import com.aiyangniu.mall.enter.model.bo.SmsCouponHistoryDetail;
import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import com.aiyangniu.mall.enter.model.pojo.SmsCoupon;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponHistory;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponProductCategoryRelation;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponProductRelation;
import com.aiyangniu.mall.enter.model.pojo.UmsMember;
import com.aiyangniu.mall.enter.service.UmsMemberCouponService;
import com.aiyangniu.mall.enter.service.UmsMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 用户优惠券管理实现类
 *
 * @author lzq
 * @date 2023/05/24
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsMemberCouponServiceImpl implements UmsMemberCouponService {

    private final UmsMemberService umsMemberService;
    private final SmsCouponHistoryMapper smsCouponHistoryMapper;
    private final SmsCouponMapper smsCouponMapper;
    private final SmsCouponProductRelationMapper smsCouponProductRelationMapper;
    private final PmsProductMapper pmsProductMapper;
    private final SmsCouponProductCategoryRelationMapper smsCouponProductCategoryRelationMapper;

    private final UmsMemberMapper umsMemberMapper;

    @Override
    public List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type) {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        Date now = new Date();
        // 获取该用户所有优惠券
        List<SmsCouponHistoryDetail> allList = smsCouponHistoryMapper.getDetailList(currentMember.getId());
        // 根据优惠券使用类型来判断优惠券是否可用
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            Integer useType = couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint = couponHistoryDetail.getCoupon().getMinPoint();
            Date endTime = couponHistoryDetail.getCoupon().getEndTime();
            if(useType.equals(0)){
                // 0->全场通用
                // 判断是否满足优惠起点
                // 计算购物车商品的总价
                BigDecimal totalAmount = calcTotalAmount(cartItemList);
                if(now.before(endTime) && totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(1)){
                // 1->指定分类
                // 计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(cartItemList, productCategoryIds);
                if(now.before(endTime) && totalAmount.intValue() > 0 && totalAmount.subtract(minPoint).intValue() >= 0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(2)){
                // 2->指定商品
                // 计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(cartItemList, productIds);
                if(now.before(endTime) && totalAmount.intValue() >0 && totalAmount.subtract(minPoint).intValue() >= 0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if(type.equals(1)){
            return enableList;
        }else{
            return disableList;
        }
    }

    private BigDecimal calcTotalAmount(List<CartPromotionItem> cartItemList) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
            total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    private BigDecimal calcTotalAmountByproductCategoryId(List<CartPromotionItem> cartItemList, List<Long> productCategoryIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productCategoryIds.contains(item.getProductCategoryId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calcTotalAmountByProductId(List<CartPromotionItem> cartItemList, List<Long> productIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productIds.contains(item.getProductId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    @Override
    public void add(Long couponId) {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        // 获取优惠券信息，判断数量
        SmsCoupon coupon = smsCouponMapper.selectById(couponId);
        if(coupon == null){
            Asserts.fail("优惠券不存在");
        }
        if(coupon.getCount() <= 0){
            Asserts.fail("优惠券已经领完了");
        }
        Date now = new Date();
        if(now.before(coupon.getEnableTime())){
            Asserts.fail("优惠券还没到领取时间");
        }
        // 判断用户领取的优惠券数量是否超过限制
        long count = smsCouponHistoryMapper.selectCount(new LambdaQueryWrapper<SmsCouponHistory>().eq(SmsCouponHistory::getCouponId, couponId).eq(SmsCouponHistory::getMemberId, currentMember.getId()));
        if(count >= coupon.getPerLimit()){
            Asserts.fail("您已经领取过该优惠券");
        }
        // 生成领取优惠券历史
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setCouponId(couponId);
        couponHistory.setCouponCode(generateCouponCode(currentMember.getId()));
        couponHistory.setCreateTime(now);
        couponHistory.setMemberId(currentMember.getId());
        couponHistory.setMemberNickname(currentMember.getNickname());
        // 主动领取
        couponHistory.setGetType(1);
        // 未使用
        couponHistory.setUseStatus(0);
        smsCouponHistoryMapper.insert(couponHistory);
        // 修改优惠券表的数量、领取数量
        coupon.setCount(coupon.getCount() - 1);
        coupon.setReceiveCount(coupon.getReceiveCount() == null ? 1 : coupon.getReceiveCount() + 1);
        smsCouponMapper.updateById(coupon);
    }

    /**
     * 16位优惠码生成：时间戳后8位+4位随机数+用户id后4位
     */
    private String generateCouponCode(Long memberId) {
        StringBuilder sb = new StringBuilder();
        Long currentTimeMillis = System.currentTimeMillis();
        String timeMillisStr = currentTimeMillis.toString();
        sb.append(timeMillisStr.substring(timeMillisStr.length() - 8));
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }
        String memberIdStr = memberId.toString();
        if (memberIdStr.length() <= 4) {
            sb.append(String.format("%04d", memberId));
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length()-4));
        }
        return sb.toString();
    }

    @Override
    public List<SmsCouponHistory> listHistory(Integer useStatus) {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        return smsCouponHistoryMapper.selectList(new LambdaQueryWrapper<SmsCouponHistory>().eq(SmsCouponHistory::getMemberId, currentMember.getId()).eq(useStatus != null, SmsCouponHistory::getUseStatus, useStatus));
    }

    @Override
    public List<SmsCoupon> list(Integer useStatus) {
        UmsMember member = umsMemberService.getCurrentMember();
        return smsCouponHistoryMapper.getCouponList(member.getId(), useStatus);
    }

    @Override
    public List<SmsCoupon> listByProduct(Long productId) {
        List<Long> allCouponIds = new ArrayList<>();
        // 获取指定商品优惠券
        List<SmsCouponProductRelation> cprList = smsCouponProductRelationMapper.selectList(new LambdaQueryWrapper<SmsCouponProductRelation>().eq(SmsCouponProductRelation::getProductId, productId));
        if (CollUtil.isNotEmpty(cprList)) {
            List<Long> couponIds = cprList.stream().map(SmsCouponProductRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }
        // 获取指定分类优惠券
        PmsProduct product = pmsProductMapper.selectById(productId);
        List<SmsCouponProductCategoryRelation> cpcrList = smsCouponProductCategoryRelationMapper.selectList(new LambdaQueryWrapper<SmsCouponProductCategoryRelation>().eq(SmsCouponProductCategoryRelation::getProductCategoryId, product.getProductCategoryId()));
        if (CollUtil.isNotEmpty(cpcrList)) {
            List<Long> couponIds = cpcrList.stream().map(SmsCouponProductCategoryRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }
        // 所有优惠券
        return smsCouponMapper.selectList(new LambdaQueryWrapper<SmsCoupon>()
                .ge(SmsCoupon::getEndTime, new Date())
                .le(SmsCoupon::getStartTime, new Date())
                .eq(SmsCoupon::getUseType, 0)
                .or(CollUtil.isNotEmpty(allCouponIds))
                .ge(SmsCoupon::getEndTime, new Date())
                .le(SmsCoupon::getStartTime, new Date())
                .eq(SmsCoupon::getUseType, 0)
                .in(SmsCoupon::getId, allCouponIds)
        );
    }
}
