package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.UmsRoleMenuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台角色菜单关系Mapper
 *
 * @author lzq
 * @date 2023/06/08
 */
public interface UmsRoleMenuRelationMapper extends BaseMapper<UmsRoleMenuRelation> {

    /**
     * 给角色批量分配菜单
     *
     * @param list 订单操作记录
     * @return 创建数量
     */
    int insertList(@Param("list") List<UmsRoleMenuRelation> list);
}
