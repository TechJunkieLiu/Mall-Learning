package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.PmsSkuStock;
import com.aiyangniu.mall.enter.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品SKU库存管理
 *
 * @author lzq
 * @date 2023/06/01
 */
@Api(value = "PmsSkuStockController", tags = "商品SKU库存管理")
@RestController
@RequestMapping("/sku")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PmsSkuStockController {

    private final PmsSkuStockService pmsSkuStockService;

    @ApiOperation("根据商品ID及sku编码模糊搜索sku库存")
    @GetMapping(value = "/{pid}")
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword", required = false) String keyword) {
        List<PmsSkuStock> skuStockList = pmsSkuStockService.getList(pid, keyword);
        return CommonResult.success(skuStockList);
    }

    @ApiOperation("批量更新sku库存信息")
    @PostMapping(value ="/update/{pid}")
    public CommonResult update(@PathVariable Long pid, @RequestBody List<PmsSkuStock> skuStockList){
        int count = pmsSkuStockService.update(pid, skuStockList);
        if(count > 0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
