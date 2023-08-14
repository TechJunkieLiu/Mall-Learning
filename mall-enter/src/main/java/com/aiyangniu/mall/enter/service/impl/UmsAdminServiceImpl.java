package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.common.exception.Asserts;
import com.aiyangniu.mall.common.util.IpAddressUtil;
import com.aiyangniu.mall.common.util.SpringUtil;
import com.aiyangniu.mall.enter.mapper.UmsAdminLoginLogMapper;
import com.aiyangniu.mall.enter.mapper.UmsAdminMapper;
import com.aiyangniu.mall.enter.mapper.UmsAdminRoleRelationMapper;
import com.aiyangniu.mall.enter.model.bo.AdminUserDetails;
import com.aiyangniu.mall.enter.model.dto.UmsAdminDTO;
import com.aiyangniu.mall.enter.model.dto.UpdateAdminPasswordDTO;
import com.aiyangniu.mall.enter.model.pojo.*;
import com.aiyangniu.mall.enter.service.UmsAdminCacheService;
import com.aiyangniu.mall.enter.service.UmsAdminService;
import com.aiyangniu.mall.security.util.JwtTokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台用户实现类
 *
 * @author lzq
 * @date 2023/04/25
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsAdminServiceImpl implements UmsAdminService {

    private final UmsAdminMapper umsAdminMapper;
    private final PasswordEncoder passwordEncoder;
    private final UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public UmsAdmin register(UmsAdminDTO dto) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(dto, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        // 查询是否有相同用户名的用户
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectList(new LambdaQueryWrapper<UmsAdmin>().eq(UmsAdmin::getUsername, umsAdmin.getUsername()));
        if (umsAdminList.size() > 0) {
            return null;
        }
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password, userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        // 获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        // 先从缓存中获取数据
        UmsAdmin admin = getCacheService().getAdmin(username);
        if(admin != null) {
            return admin;
        }
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectList(new LambdaQueryWrapper<UmsAdmin>().eq(UmsAdmin::getUsername, username));
        if (umsAdminList != null && umsAdminList.size() > 0) {
            admin = umsAdminList.get(0);
            // 数据写入缓存
            getCacheService().setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        // 先从缓存中获取数据
        List<UmsResource> resourceList = getCacheService().getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = umsAdminRoleRelationMapper.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            // 数据写入缓存
            getCacheService().setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return umsAdminRoleRelationMapper.getRoleList(adminId);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<UmsAdmin> lqw = new LambdaQueryWrapper<UmsAdmin>().like(!StrUtil.isEmpty(keyword), UmsAdmin::getUsername, keyword).or().like(!StrUtil.isEmpty(keyword), UmsAdmin::getNickName, keyword);
        return umsAdminMapper.selectList(lqw);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return umsAdminMapper.selectById(id);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        UmsAdmin umsAdmin = umsAdminMapper.selectById(id);
        if(umsAdmin.getPassword().equals(admin.getPassword())){
            // 与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{
            // 与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else{
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        int count = umsAdminMapper.updateById(admin);
        getCacheService().delAdmin(id);
        return count;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordDTO dto) {
        if(StrUtil.isEmpty(dto.getUsername()) || StrUtil.isEmpty(dto.getOldPassword()) || StrUtil.isEmpty(dto.getNewPassword())){
            return -1;
        }
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectList(new LambdaQueryWrapper<UmsAdmin>().eq(UmsAdmin::getUsername, dto.getUsername()));
        if(CollUtil.isEmpty(umsAdminList)){
            return -2;
        }
        UmsAdmin umsAdmin = umsAdminList.get(0);
        if(!passwordEncoder.matches(dto.getOldPassword(), umsAdmin.getPassword())){
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        umsAdminMapper.updateById(umsAdmin);
        getCacheService().delAdmin(umsAdmin.getId());
        return 1;
    }

    @Override
    public int delete(Long id) {
        getCacheService().delAdmin(id);
        int count = umsAdminMapper.deleteById(id);
        getCacheService().delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        // 先删除原来的关系
        umsAdminRoleRelationMapper.deleteById(adminId);
        // 建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            umsAdminRoleRelationMapper.insertList(list);
        }
        getCacheService().delResourceList(adminId);
        return count;
    }

    /**
     * 添加登录记录
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin == null) {
            return;
        }
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(IpAddressUtil.getIpAddress(request));
        umsAdminLoginLogMapper.insert(loginLog);
    }
}
