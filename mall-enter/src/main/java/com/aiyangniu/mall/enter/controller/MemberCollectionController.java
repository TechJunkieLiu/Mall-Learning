package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.bo.MemberProductCollection;
import com.aiyangniu.mall.enter.service.MemberCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 会员收藏管理
 *
 * @author lzq
 * @date 2023/06/30
 */
@Api(value = "MemberCollectionController", tags = "会员收藏管理")
@RestController
@RequestMapping("/member/productCollection")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberCollectionController {

    private MemberCollectionService memberCollectionService;

    @ApiOperation("添加商品收藏")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody MemberProductCollection productCollection) {
        int count = memberCollectionService.add(productCollection);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除商品收藏")
    @PostMapping(value = "/delete")
    public CommonResult delete(Long productId) {
        int count = memberCollectionService.delete(productId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("显示当前用户商品收藏列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<MemberProductCollection>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<MemberProductCollection> page = memberCollectionService.list(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("显示商品收藏详情")
    @GetMapping(value = "/detail")
    public CommonResult<MemberProductCollection> detail(@RequestParam Long productId) {
        MemberProductCollection memberProductCollection = memberCollectionService.detail(productId);
        return CommonResult.success(memberProductCollection);
    }

    @ApiOperation("清空当前用户商品收藏列表")
    @PostMapping(value = "/clear")
    public CommonResult clear() {
        memberCollectionService.clear();
        return CommonResult.success(null);
    }
}
