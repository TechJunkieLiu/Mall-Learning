package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.UmsAdmin;
import com.aiyangniu.mall.enter.model.pojo.UmsResource;

import java.util.List;

/**
 * 后台用户缓存操作接口
 *
 * @author lzq
 * @date 2023/04/25
 */
public interface UmsAdminCacheService {

    /**
     * 获取缓存后台用户资源列表
     *
     * @param adminId 用户ID
     * @return 用户资源列表
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置缓存后台用户资源列表
     *
     * @param adminId 用户ID
     * @param resourceList 资源列表
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);

    /**
     * 获取缓存后台用户信息
     *
     * @param username 用户名称
     * @return 用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     *
     * @param admin 用户信息
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 删除后台用户缓存
     *
     * @param adminId 用户ID
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     *
     * @param id 用户ID
     */
    void delResourceList(Long id);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     *
     * @param roleId 角色ID
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     *
     * @param roleIds 角色IDS
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     *
     * @param resourceId 资源ID
     */
    void delResourceListByResource(Long resourceId);
}
