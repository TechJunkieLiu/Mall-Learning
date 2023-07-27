package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.PmsProductAttributeCategoryMapper;
import com.aiyangniu.mall.enter.model.bo.PmsProductAttributeCategoryItem;
import com.aiyangniu.mall.enter.model.pojo.PmsProductAttributeCategory;
import com.aiyangniu.mall.enter.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性分类管理实现类
 *
 * @author lzq
 * @date 2023/05/30
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    private final PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insert(productAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return productAttributeCategoryMapper.updateById(productAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteById(id);
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryMapper.selectById(id);
    }

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return productAttributeCategoryMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }
}
