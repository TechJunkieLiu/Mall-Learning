package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponHistory;
import com.aiyangniu.mall.enter.service.SmsCouponHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券领取记录管理
 *
 * @author lzq
 * @date 2023/06/01
 */
@Api(value = "SmsCouponHistoryController", tags = "优惠券领取记录管理")
@RestController
@RequestMapping("/couponHistory")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SmsCouponHistoryController {

    private final SmsCouponHistoryService smsCouponHistoryService;

    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SmsCouponHistory>> list(
            @RequestParam(value = "couponId", required = false) Long couponId,
            @RequestParam(value = "useStatus", required = false) Integer useStatus,
            @RequestParam(value = "orderSn", required = false) String orderSn,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<SmsCouponHistory> historyList = smsCouponHistoryService.list(couponId, useStatus, orderSn, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(historyList));
    }
}
