package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.bo.PmsProductAttributeCategoryItem;
import com.aiyangniu.mall.enter.model.pojo.PmsProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分类管理接口
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface PmsProductAttributeCategoryService {

    /**
     * 创建属性分类
     *
     * @param name 属性分类名称
     * @return 创建个数
     */
    int create(String name);

    /**
     * 修改属性分类
     *
     * @param id 属性分类ID
     * @param name 属性分类名称
     * @return 修改个数
     */
    int update(Long id, String name);

    /**
     * 删除属性分类
     *
     * @param id 属性分类ID
     * @return 删除个数
     */
    int delete(Long id);

    /**
     * 获取属性分类详情
     *
     * @param id 属性分类ID
     * @return 属性分类详情
     */
    PmsProductAttributeCategory getItem(Long id);

    /**
     * 分页查询属性分类
     *
     * @param pageSize 页条数
     * @param pageNum 当前页
     * @return 属性分类列表
     */
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 获取包含属性的属性分类
     *
     * @return 属性分类列表（包含属性）
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
