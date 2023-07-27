package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.dto.UmsAdminDTO;
import com.aiyangniu.mall.enter.model.dto.UpdateAdminPasswordDTO;
import com.aiyangniu.mall.enter.model.pojo.UmsAdmin;
import com.aiyangniu.mall.enter.model.pojo.UmsResource;
import com.aiyangniu.mall.enter.model.pojo.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台用户接口
 *
 * @author lzq
 * @date 2023/04/25
 */
public interface UmsAdminService {

    /**
     * 注册功能
     *
     * @param dto 用户注册参数
     * @return 用户信息
     */
    UmsAdmin register(UmsAdminDTO dto);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
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
     * 根据用户名获取后台管理员
     *
     * @param username 用户名
     * @return 后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取缓存服务
     *
     * @return 缓存用户信息
     */
    UmsAdminCacheService getCacheService();

    /**
     * 获取指定用户的可访问资源
     *
     * @param adminId 用户ID
     * @return 用户资源列表
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     * @return 新的token
     */
    String refreshToken(String oldToken);

    /**
     * 获取用户对应角色
     *
     * @param adminId 用户ID
     * @return 角色列表
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword 用户名或昵称
     * @param pageNum 当前页
     * @param pageSize 页条数
     * @return 用户列表
     */
    List<UmsAdmin> list(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UmsAdmin getItem(Long id);

    /**
     * 修改指定用户信息
     *
     * @param id 用户ID
     * @param admin 用户信息
     * @return 修改数量
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 修改指定用户密码
     *
     * @param updateAdminPasswordDTO 用户名密码参数
     * @return 修改数量
     */
    int updatePassword(UpdateAdminPasswordDTO updateAdminPasswordDTO);

    /**
     * 删除指定用户
     *
     * @param id 用户ID
     * @return 删除数量
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     *
     * @param adminId 用户ID
     * @param roleIds 角色列表
     * @return 修改数量
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    int updateRole(Long adminId, List<Long> roleIds);
}
