package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员登录注册接口
 *
 * @author lzq
 * @date 2023/05/08
 */
public interface UmsMemberService {

    /**
     * 会员注册
     *
     * @param username 用户名
     * @param password 密码
     * @param telephone 手机号
     * @param authCode 验证码
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    void register(String username, String password, String telephone, String authCode);

    /**
     * 会员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取会员
     *
     * @param username 用户名
     * @return 会员信息
     */
    UmsMember getByUsername(String username);

    /**
     * 获取当前登录会员
     *
     * @return 会员信息
     */
    UmsMember getCurrentMember();

    /**
     * 生成验证码
     *
     * @param telephone 手机号
     * @return 验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     *
     * @param telephone 手机号
     * @param password 密码
     * @param authCode 验证码
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    void updatePassword(String telephone, String password, String authCode);

    /**
     * 刷新token
     *
     * @param token 旧的token
     * @return 新的token
     */
    String refreshToken(String token);

    /**
     * 根据会员编号获取会员
     *
     * @param id 会员ID
     * @return 会员信息
     */
    UmsMember getById(Long id);

    /**
     * 根据会员id修改会员积分
     *
     * @param id 会员ID
     * @param integration 会员积分
     */
    void updateIntegration(Long id, Integer integration);
}
