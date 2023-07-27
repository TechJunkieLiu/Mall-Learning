package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.OmsCompanyAddress;
import com.aiyangniu.mall.enter.service.OmsCompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收货地址管理
 *
 * @author lzq
 * @date 2023/05/22
 */
@Api(tags = "OmsCompanyAddressController")
@Tag(name = "OmsCompanyAddressController", description = "收货地址管理")
@RestController
@RequestMapping("/companyAddress")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsCompanyAddressController {

    private final OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @GetMapping(value = "/list")
    @ResponseBody
    public CommonResult<List<OmsCompanyAddress>> list() {
        List<OmsCompanyAddress> companyAddressList = companyAddressService.list();
        return CommonResult.success(companyAddressList);
    }
}
