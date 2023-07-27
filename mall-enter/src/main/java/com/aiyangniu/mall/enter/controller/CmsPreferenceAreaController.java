package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.CmsPreferenceArea;
import com.aiyangniu.mall.enter.service.CmsPreferenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品优选管理
 *
 * @author lzq
 * @date 2023/06/01
 */
@Api(tags = "CmsPreferenceAreaController")
@Tag(name = "CmsPreferenceAreaController", description = "商品优选管理")
@RestController
@RequestMapping("/preferenceArea")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CmsPreferenceAreaController {

    private final CmsPreferenceAreaService cmsPreferenceAreaService;

    @ApiOperation("获取所有商品优选")
    @GetMapping(value = "/listAll")
    public CommonResult<List<CmsPreferenceArea>> listAll() {
        List<CmsPreferenceArea> list = cmsPreferenceAreaService.listAll();
        return CommonResult.success(list);
    }
}
