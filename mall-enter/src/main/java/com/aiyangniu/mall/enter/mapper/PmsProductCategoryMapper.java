package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.PmsProductCategoryWithChildrenItem;
import com.aiyangniu.mall.enter.model.pojo.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 商品分类管理Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    /**
     * 获取商品分类及其子分类
     *
     * @return 商品分类及其子分类
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
