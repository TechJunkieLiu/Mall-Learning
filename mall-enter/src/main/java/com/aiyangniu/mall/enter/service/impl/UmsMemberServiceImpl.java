package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.common.exception.Asserts;
import com.aiyangniu.mall.enter.mapper.UmsMemberLevelMapper;
import com.aiyangniu.mall.enter.mapper.UmsMemberMapper;
import com.aiyangniu.mall.enter.model.bo.MemberDetails;
import com.aiyangniu.mall.enter.model.pojo.UmsMember;
import com.aiyangniu.mall.enter.model.pojo.UmsMemberLevel;
import com.aiyangniu.mall.enter.service.UmsMemberCacheService;
import com.aiyangniu.mall.enter.service.UmsMemberService;
import com.aiyangniu.mall.security.util.JwtTokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员登录注册实现类
 *
 * @author lzq
 * @date 2023/05/08
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsMemberServiceImpl implements UmsMemberService  {

    private final UmsMemberCacheService umsMemberCacheService;
    private final UmsMemberMapper umsMemberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UmsMemberLevelMapper umsMemberLevelMapper;

    @Override
    public void register(String username, String password, String telephone, String authCode) {
        // 验证验证码
        if(!verifyAuthCode(authCode, telephone)){
            Asserts.fail("验证码错误");
        }
        // 查询是否已有该用户
        LambdaQueryWrapper<UmsMember> lqw1 = new LambdaQueryWrapper<UmsMember>().eq(UmsMember::getUsername, username).or().eq(UmsMember::getPhone, telephone);
        List<UmsMember> umsMemberList = umsMemberMapper.selectList(lqw1);

        if (!CollectionUtils.isEmpty(umsMemberList)) {
            Asserts.fail("该用户已经存在");
        }
        // 没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);

        // 获取默认会员等级并设置
        LambdaQueryWrapper<UmsMemberLevel> lqw2 = new LambdaQueryWrapper<UmsMemberLevel>().eq(UmsMemberLevel::getDefaultStatus, 1);
        List<UmsMemberLevel> umsMemberLevelList = umsMemberLevelMapper.selectList(lqw2);
        if (!CollectionUtils.isEmpty(umsMemberLevelList)) {
            umsMember.setMemberLevelId(umsMemberLevelList.get(0).getId());
        }
        umsMemberMapper.insert(umsMember);
        umsMember.setPassword(null);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password, userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 对输入的验证码进行校验
     */
    private boolean verifyAuthCode(String authCode, String telephone){
        if(StrUtil.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = umsMemberCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if(member != null){
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public UmsMember getByUsername(String username) {
        UmsMember member = umsMemberCacheService.getMember(username);
        if (member != null) {
            return member;
        }
        List<UmsMember> umsMemberList = umsMemberMapper.selectList(new LambdaQueryWrapper<UmsMember>().eq(UmsMember::getUsername, username));
        if (!CollectionUtils.isEmpty(umsMemberList)) {
            member = umsMemberList.get(0);
            umsMemberCacheService.setMember(member);
            return member;
        }
        return null;
    }

    @Override
    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getUmsMember();
    }

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        String authCode = sb.toString();
        umsMemberCacheService.setAuthCode(telephone, authCode);
        return sb.toString();
    }

    @Override
    public void updatePassword(String telephone, String password, String authCode) {
        List<UmsMember> umsMemberList = umsMemberMapper.selectList(new LambdaQueryWrapper<UmsMember>().eq(UmsMember::getPhone, telephone));
        if(CollectionUtils.isEmpty(umsMemberList)){
            Asserts.fail("该账号不存在");
        }
        // 验证验证码
        if(!verifyAuthCode(authCode, telephone)){
            Asserts.fail("验证码错误");
        }
        UmsMember umsMember = umsMemberList.get(0);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMemberMapper.updateById(umsMember);
        umsMemberCacheService.delMember(umsMember.getId());
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public UmsMember getById(Long id) {
        return umsMemberMapper.selectById(id);
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        umsMemberMapper.updateById(record);
        umsMemberCacheService.delMember(id);
    }
}
