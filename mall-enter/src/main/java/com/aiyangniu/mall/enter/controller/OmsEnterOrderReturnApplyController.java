package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.bo.OmsOrderReturnApplyParam;
import com.aiyangniu.mall.enter.service.OmsEnterOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 前台订单退货管理
 *
 * @author lzq
 * @date 2023/07/21
 */
@Api(value = "OmsEnterOrderReturnApplyController", tags = "前台订单退货管理")
@RestController
@RequestMapping("/enterReturnApply")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsEnterOrderReturnApplyController {

    private final OmsEnterOrderReturnApplyService returnApplyService;

    @ApiOperation("申请退货")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody OmsOrderReturnApplyParam returnApply) {
        int count = returnApplyService.create(returnApply);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
