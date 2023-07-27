package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.OmsOrderSetting;

/**
 * 订单设置管理接口
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsOrderSettingService {

    /**
     * 获取指定订单设置
     *
     * @param id 订单设置ID
     * @return 订单设置
     */
    OmsOrderSetting getItem(Long id);

    /**
     * 修改指定订单设置
     *
     * @param id 订单设置ID
     * @param orderSetting 订单设置
     * @return 修改个数
     */
    int update(Long id, OmsOrderSetting orderSetting);
}
