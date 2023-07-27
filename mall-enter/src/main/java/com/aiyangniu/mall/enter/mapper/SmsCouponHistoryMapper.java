package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.SmsCouponHistoryDetail;
import com.aiyangniu.mall.enter.model.pojo.SmsCoupon;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券使用、领取历史Mapper
 *
 * @author lzq
 * @date 2023/05/24
 */
public interface SmsCouponHistoryMapper extends BaseMapper<SmsCouponHistory> {

    /**
     * 获取优惠券历史详情
     *
     * @param memberId 会员ID
     * @return 优惠券历史详情
     */
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);

    /**
     * 获取指定会员优惠券列表
     *
     * @param memberId 会员ID
     * @param useStatus 优惠券筛选类型
     * @return 优惠券列表
     */
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus") Integer useStatus);
}
