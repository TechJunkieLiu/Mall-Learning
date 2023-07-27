package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.UmsResourceCategory;

import java.util.List;

/**
 * 后台资源分类管理接口
 *
 * @author lzq
 * @date 2023/06/08
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源分类
     *
     * @return 资源分类列表
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     *
     * @param umsResourceCategory 资源分类
     * @return 创建个数
     */
    int create(UmsResourceCategory umsResourceCategory);

    /**
     * 修改资源分类
     *
     * @param id 资源分类ID
     * @param umsResourceCategory 资源分类
     * @return 修改个数
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 删除资源分类
     *
     * @param id 资源分类ID
     * @return 删除个数
     */
    int delete(Long id);
}
