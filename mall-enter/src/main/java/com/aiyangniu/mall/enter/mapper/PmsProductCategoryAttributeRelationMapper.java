package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.PmsProductCategoryAttributeRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类和属性关系Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface PmsProductCategoryAttributeRelationMapper extends BaseMapper<PmsProductCategoryAttributeRelation> {

    /**
     * 批量创建
     *
     * @param list 用户ID
     * @return 可访问资源列表
     */
    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> list);
}
