package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.PmsProductAttributeCategoryItem;
import com.aiyangniu.mall.enter.model.pojo.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 商品属性分类管理Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {

    /**
     * 获取包含属性的商品属性分类
     *
     * @return 商品分类列表（包含属性）
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
