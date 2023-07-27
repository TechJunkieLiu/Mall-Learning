package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.UmsMenu;
import com.aiyangniu.mall.enter.model.pojo.UmsResource;
import com.aiyangniu.mall.enter.model.pojo.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台角色Mapper
 *
 * @author lzq
 * @date 2023/05/06
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 根据后台用户ID获取菜单
     *
     * @param adminId 用户ID
     * @return 菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * 根据角色ID获取菜单
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色ID获取资源
     *
     * @param roleId 角色ID
     * @return 资源列表
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
