package com.aiyangniu.mall.enter.controller;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.pojo.UmsMember;
import com.aiyangniu.mall.enter.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员登录注册管理
 *
 * @author lzq
 * @date 2023/04/27
 */
@Api(value = "UmsMemberController", tags = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsMemberController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final UmsMemberService umsMemberService;

    @ApiOperation("会员注册")
    @PostMapping(value = "/register")
    public CommonResult register(@RequestParam String username, @RequestParam String password, @RequestParam String telephone, @RequestParam String authCode) {
        umsMemberService.register(username, password, telephone, authCode);
        return CommonResult.success(null,"注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping(value = "/login")
    public CommonResult login(@RequestParam String username, @RequestParam String password) {
        String token = umsMemberService.login(username, password);
        if (!StringUtils.hasText(token)) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(16);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取会员信息")
    @GetMapping(value = "/info")
    public CommonResult info(Principal principal) {
        if(ObjectUtils.isEmpty(principal)){
            return CommonResult.unauthorized(null);
        }
        UmsMember member = umsMemberService.getCurrentMember();
        return CommonResult.success(member);
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone) {
        String authCode = umsMemberService.generateAuthCode(telephone);
        return CommonResult.success(authCode,"获取验证码成功");
    }

    @ApiOperation("会员修改密码")
    @PostMapping(value = "/updatePassword")
    public CommonResult updatePassword(@RequestParam String telephone, @RequestParam String password, @RequestParam String authCode) {
        umsMemberService.updatePassword(telephone, password, authCode);
        return CommonResult.success(null,"密码修改成功");
    }

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = umsMemberService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>(16);
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
