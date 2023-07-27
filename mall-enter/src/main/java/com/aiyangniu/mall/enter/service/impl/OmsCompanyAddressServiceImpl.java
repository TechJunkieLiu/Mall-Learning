package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.OmsCompanyAddressMapper;
import com.aiyangniu.mall.enter.model.pojo.OmsCompanyAddress;
import com.aiyangniu.mall.enter.service.OmsCompanyAddressService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址管理实现类
 *
 * @author lzq
 * @date 2023/05/24
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {

    private final OmsCompanyAddressMapper omsCompanyAddressMapper;

    @Override
    public List<OmsCompanyAddress> list() {
        return omsCompanyAddressMapper.selectList(new LambdaQueryWrapper<>());
    }
}
