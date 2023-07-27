package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.UmsMemberLevel;
import com.aiyangniu.mall.enter.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级管理
 *
 * @author lzq
 * @date 2023/06/15
 */
@Api(tags = "UmsMemberLevelController")
@Tag(name = "UmsMemberLevelController", description = "会员等级管理")
@RestController
@RequestMapping("/memberLevel")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsMemberLevelController {

    private final UmsMemberLevelService memberLevelService;

    @ApiOperation("查询所有会员等级")
    @GetMapping(value = "/list")
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return CommonResult.success(memberLevelList);
    }
}
