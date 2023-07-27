package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.bo.MemberBrandAttention;
import com.aiyangniu.mall.enter.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 会员关注品牌管理
 *
 * @author lzq
 * @date 2023/06/30
 */
@Api(tags = "MemberAttentionController")
@Tag(name = "MemberAttentionController", description = "会员关注品牌管理")
@RestController
@RequestMapping("/member/attention")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberAttentionController {

    private final MemberAttentionService memberAttentionService;

    @ApiOperation("添加品牌关注")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody MemberBrandAttention memberBrandAttention) {
        int count = memberAttentionService.add(memberBrandAttention);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("取消品牌关注")
    @PostMapping(value = "/delete")
    public CommonResult delete(Long brandId) {
        int count = memberAttentionService.delete(brandId);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("显示当前用户品牌关注列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<MemberBrandAttention>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<MemberBrandAttention> page = memberAttentionService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("显示品牌关注详情")
    @GetMapping(value = "/detail")
    public CommonResult<MemberBrandAttention> detail(@RequestParam Long brandId) {
        MemberBrandAttention memberBrandAttention = memberAttentionService.detail(brandId);
        return CommonResult.success(memberBrandAttention);
    }

    @ApiOperation("清空当前用户品牌关注列表")
    @PostMapping(value = "/clear")
    public CommonResult clear() {
        memberAttentionService.clear();
        return CommonResult.success(null);
    }
}
