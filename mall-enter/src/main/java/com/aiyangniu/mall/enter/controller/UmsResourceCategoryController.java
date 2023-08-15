package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.UmsResourceCategory;
import com.aiyangniu.mall.enter.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源分类管理
 *
 * @author lzq
 * @date 2023/06/08
 */
@Api(value = "UmsResourceCategoryController", tags = "后台资源分类管理")
@RestController
@RequestMapping("/resourceCategory")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsResourceCategoryController {

    private final UmsResourceCategoryService umsResourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @GetMapping(value = "/listAll")
    public CommonResult<List<UmsResourceCategory>> listAll() {
        List<UmsResourceCategory> resourceList = umsResourceCategoryService.listAll();
        return CommonResult.success(resourceList);
    }

    @ApiOperation("添加后台资源分类")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody UmsResourceCategory umsResourceCategory) {
        int count = umsResourceCategoryService.create(umsResourceCategory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsResourceCategory umsResourceCategory) {
        int count = umsResourceCategoryService.update(id, umsResourceCategory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = umsResourceCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
