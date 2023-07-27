package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.enter.mapper.SmsCouponHistoryMapper;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponHistory;
import com.aiyangniu.mall.enter.service.SmsCouponHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 惠券领取记录管理实现类
 *
 * @author lzq
 * @date 2023/06/01
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {

    @Override
    public List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return list(new LambdaQueryWrapper<SmsCouponHistory>().eq(couponId != null, SmsCouponHistory::getCouponId, couponId).eq(useStatus != null, SmsCouponHistory::getUseStatus, useStatus).eq(!StrUtil.isEmpty(orderSn), SmsCouponHistory::getOrderSn, orderSn));
    }
}
