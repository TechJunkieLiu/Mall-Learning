package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.SmsCouponHistory;

import java.util.List;

/**
 * 优惠券领取记录管理接口
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface SmsCouponHistoryService {

    /**
     * 分页查询优惠券领取记录
     *
     * @param couponId 优惠券id
     * @param useStatus 使用状态
     * @param orderSn 使用订单号码
     * @param pageSize 页条数
     * @param pageNum 当前页
     * @return 优惠券领取记录
     */
    List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}
