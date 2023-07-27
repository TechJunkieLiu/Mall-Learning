package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.OmsOrderSettingMapper;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderSetting;
import com.aiyangniu.mall.enter.service.OmsOrderSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单设置管理实现类
 *
 * @author lzq
 * @date 2023/06/01
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    private final OmsOrderSettingMapper orderSettingMapper;

    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectById(id);
    }

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingMapper.updateById(orderSetting);
    }
}
