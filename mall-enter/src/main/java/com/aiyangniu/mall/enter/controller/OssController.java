package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.bo.OssCallbackResult;
import com.aiyangniu.mall.enter.model.bo.OssPolicyResult;
import com.aiyangniu.mall.enter.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理
 *
 * @author lzq
 * @date 2023/05/29
 */
@Api(value = "OssController", tags = "Oss 对象存储管理")
@RestController
@RequestMapping("/aliyun/oss")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OssController {

    private final OssService ossService;

    @ApiOperation(value = "Oss上传签名生成")
    @GetMapping(value = "/policy")
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "Oss上传成功回调")
    @PostMapping(value = "callback")
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }
}
