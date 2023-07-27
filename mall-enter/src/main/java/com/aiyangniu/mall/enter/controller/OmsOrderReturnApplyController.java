package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.bo.OmsOrderReturnApplyResult;
import com.aiyangniu.mall.enter.model.dto.OmsReturnApplyQueryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsUpdateStatusParamDTO;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnApply;
import com.aiyangniu.mall.enter.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退货申请管理
 *
 * @author lzq
 * @date 2023/06/01
 */
@Api(tags = "OmsOrderReturnApplyController")
@Tag(name = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@RestController
@RequestMapping("/returnApply")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsOrderReturnApplyController {

    private final OmsOrderReturnApplyService omsOrderReturnApplyService;

    @ApiOperation("分页查询退货申请")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OmsOrderReturnApply>> list(
            OmsReturnApplyQueryParamDTO queryParam,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<OmsOrderReturnApply> returnApplyList = omsOrderReturnApplyService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(returnApplyList));
    }

    @ApiOperation("批量删除退货申请")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = omsOrderReturnApplyService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取退货申请详情")
    @GetMapping(value = "/{id}")
    public CommonResult getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResult result = omsOrderReturnApplyService.getItem(id);
        return CommonResult.success(result);
    }

    @ApiOperation("修改退货申请状态")
    @PostMapping(value = "/update/status/{id}")
    public CommonResult updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParamDTO statusParam) {
        int count = omsOrderReturnApplyService.updateStatus(id, statusParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}
