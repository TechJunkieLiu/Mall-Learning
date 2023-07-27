package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.bo.OmsOrderDetail;
import com.aiyangniu.mall.enter.model.dto.OmsMoneyInfoParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsOrderDeliveryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsOrderQueryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsReceiverInfoParamDTO;
import com.aiyangniu.mall.enter.model.pojo.OmsOrder;
import com.aiyangniu.mall.enter.service.OmsOrderService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理
 *
 * @author lzq
 * @date 2023/06/01
 */
@Api(tags = "OmsOrderController-订单管理")
@Tag(name = "OmsOrderController", description = "订单管理")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsOrderController {

    private final OmsOrderService orderService;

    @ApiOperation(value = "查询订单", notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页条数", dataType = "Integer", defaultValue = "5"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer", defaultValue = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败"),
            @ApiResponse(code = 404, message = "参数检验失败"),
            @ApiResponse(code = 401, message = "暂未登录或token已经过期"),
            @ApiResponse(code = 403, message = "没有相关权限")
    })
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OmsOrder>> list(
            OmsOrderQueryParamDTO dto,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<OmsOrder> orderList = orderService.list(dto, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @ApiOperation(value = "查询订单", notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页条数", dataType = "Integer", defaultValue = "5", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer", defaultValue = "1", paramType = "path")
    })
    @GetMapping(value = "/list1/{pageSize}/{pageNum}")
    public CommonResult<CommonPage<OmsOrder>> list1(
            OmsOrderQueryParamDTO dto,
            @PathVariable @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @PathVariable @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<OmsOrder> orderList = orderService.list(dto, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @ApiOperation(value = "查询订单", notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页条数", dataType = "Integer", defaultValue = "5", paramType = "body"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer", defaultValue = "1", paramType = "body")
    })
    @GetMapping(value = "/list2")
    public CommonResult<CommonPage<OmsOrder>> list2(
            OmsOrderQueryParamDTO dto,
            @RequestBody Integer pageSize,
            @RequestBody Integer pageNum
    ) {
        List<OmsOrder> orderList = orderService.list(dto, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));
    }


    @ApiOperation("批量发货")
    @PostMapping(value = "/update/delivery")
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParamDTO> deliveryParamList) {
        int count = orderService.delivery(deliveryParamList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量关闭订单")
    @PostMapping(value = "/update/close")
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = orderService.close(ids, note);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除订单")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = orderService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取订单详情：订单信息、商品信息、操作记录")
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = orderService.detail(id);
        return CommonResult.success(orderDetailResult);
    }

    @ApiOperation("修改收货人信息")
    @PostMapping(value = "/update/receiverInfo")
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParamDTO dto) {
        int count = orderService.updateReceiverInfo(dto);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改订单费用信息")
    @PostMapping(value = "/update/moneyInfo")
    public CommonResult updateReceiverInfo(@RequestBody OmsMoneyInfoParamDTO dto) {
        int count = orderService.updateMoneyInfo(dto);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("备注订单")
    @PostMapping(value = "/update/note")
    public CommonResult updateNote(@RequestParam("id") Long id, @RequestParam("note") String note, @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id, note, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
