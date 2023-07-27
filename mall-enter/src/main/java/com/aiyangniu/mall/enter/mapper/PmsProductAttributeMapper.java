package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.PmsProductAttrInfo;
import com.aiyangniu.mall.enter.model.pojo.PmsProductAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性管理Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {

    /**
     * 获取商品分类对应属性列表
     *
     * @param productCategoryId 商品分类ID
     * @return 属性列表
     */
    List<PmsProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
