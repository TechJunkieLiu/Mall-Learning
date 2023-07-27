package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.OmsCompanyAddress;

import java.util.List;

/**
 * 收货地址管理接口
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsCompanyAddressService {

    /**
     * 获取全部收货地址
     *
     * @return 收货地址列表
     */
    List<OmsCompanyAddress> list();
}
