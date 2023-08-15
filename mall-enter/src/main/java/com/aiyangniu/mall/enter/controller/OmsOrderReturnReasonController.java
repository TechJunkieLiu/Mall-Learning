package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnReason;
import com.aiyangniu.mall.enter.service.OmsOrderReturnReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退货原因管理
 *
 * @author lzq
 * @date 2023/06/01
 */
@Api(value = "OmsOrderReturnReasonController", tags = "订单退货原因管理")
@RestController
@RequestMapping("/returnReason")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsOrderReturnReasonController {

    private final OmsOrderReturnReasonService omsOrderReturnReasonService;

    @ApiOperation("添加退货原因")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody OmsOrderReturnReason returnReason) {
        int count = omsOrderReturnReasonService.create(returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改退货原因")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderReturnReason returnReason) {
        int count = omsOrderReturnReasonService.update(id, returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除退货原因")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = omsOrderReturnReasonService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询退货原因")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OmsOrderReturnReason>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnReason> reasonList = omsOrderReturnReasonService.list(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(reasonList));
    }

    @ApiOperation("获取单个退货原因详情信息")
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderReturnReason> getItem(@PathVariable Long id) {
        OmsOrderReturnReason reason = omsOrderReturnReasonService.getItem(id);
        return CommonResult.success(reason);
    }

    @ApiOperation("修改退货原因启用状态")
    @PostMapping(value = "/update/status")
    public CommonResult updateStatus(@RequestParam(value = "status") Integer status, @RequestParam("ids") List<Long> ids) {
        int count = omsOrderReturnReasonService.updateStatus(ids, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
