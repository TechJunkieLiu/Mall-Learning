package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.PmsBrand;
import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import com.aiyangniu.mall.enter.service.PmsEnterBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台商品品牌管理
 *
 * @author lzq
 * @date 2023/07/21
 */
@Api(value = "PmsEnterBrandController", tags = "前台商品品牌管理")
@RestController
@RequestMapping("/enterBrand")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PmsEnterBrandController {

    private final PmsEnterBrandService enterBrandService;

    @ApiOperation("分页获取推荐品牌")
    @GetMapping(value = "/recommendList")
    public CommonResult<List<PmsBrand>> recommendList(@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsBrand> brandList = enterBrandService.recommendList(pageNum, pageSize);
        return CommonResult.success(brandList);
    }

    @ApiOperation("获取品牌详情")
    @GetMapping(value = "/detail/{brandId}")
    public CommonResult<PmsBrand> detail(@PathVariable Long brandId) {
        PmsBrand brand = enterBrandService.detail(brandId);
        return CommonResult.success(brand);
    }

    @ApiOperation("分页获取品牌相关商品")
    @GetMapping(value = "/productList")
    public CommonResult<CommonPage<PmsProduct>> productList(
            @RequestParam Long brandId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize
    ) {
        CommonPage<PmsProduct> result = enterBrandService.productList(brandId,pageNum, pageSize);
        return CommonResult.success(result);
    }
}
