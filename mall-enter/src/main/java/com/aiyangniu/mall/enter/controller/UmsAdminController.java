package com.aiyangniu.mall.enter.controller;

import cn.hutool.core.collection.CollUtil;
import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.dto.UmsAdminDTO;
import com.aiyangniu.mall.enter.model.dto.UmsAdminLoginDTO;
import com.aiyangniu.mall.enter.model.dto.UpdateAdminPasswordDTO;
import com.aiyangniu.mall.enter.model.pojo.UmsAdmin;
import com.aiyangniu.mall.enter.model.pojo.UmsRole;
import com.aiyangniu.mall.enter.service.UmsAdminService;
import com.aiyangniu.mall.enter.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户管理
 *
 * @author lzq
 * @date 2023/04/25
 */
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController", description = "后台用户管理")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsAdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final UmsAdminService umsAdminService;
    private final UmsRoleService umsRoleService;

    @ApiOperation("用户注册")
    @PostMapping(value = "/register")
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminDTO dto) {
        UmsAdmin umsAdmin = umsAdminService.register(dto);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation("登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResult login(@Validated @RequestBody UmsAdminLoginDTO dto) {
        String token = umsAdminService.login(dto.getUsername(), dto.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(16);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("刷新token")
    @GetMapping(value = "/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = umsAdminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>(16);
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping(value = "/info")
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>(16);
        data.put("username", umsAdmin.getUsername());
        data.put("menus", umsRoleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = umsAdminService.getRoleList(umsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation("登出功能")
    @PostMapping(value = "/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<UmsAdmin>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize
    ) {
        List<UmsAdmin> adminList = umsAdminService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/{id}")
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id) {
        UmsAdmin admin = umsAdminService.getItem(id);
        return CommonResult.success(admin);
    }

    @ApiOperation("修改指定用户信息")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdmin admin) {
        int count = umsAdminService.update(id, admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @PostMapping(value = "/updatePassword")
    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordDTO dto) {
        int status = umsAdminService.updatePassword(dto);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定用户信息")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = umsAdminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态")
    @PostMapping(value = "/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        int count = umsAdminService.update(id, umsAdmin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/update")
    public CommonResult updateRole(@RequestParam("adminId") Long adminId, @RequestParam("roleIds") List<Long> roleIds) {
        int count = umsAdminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping(value = "/role/{adminId}")
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = umsAdminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
}
