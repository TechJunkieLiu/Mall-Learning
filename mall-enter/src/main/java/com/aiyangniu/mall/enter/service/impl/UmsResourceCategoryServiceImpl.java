package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.UmsResourceCategoryMapper;
import com.aiyangniu.mall.enter.model.pojo.UmsResourceCategory;
import com.aiyangniu.mall.enter.service.UmsResourceCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源分类管理实现类
 *
 * @author lzq
 * @date 2023/06/08
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {

    private final UmsResourceCategoryMapper umsResourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> listAll() {
        return umsResourceCategoryMapper.selectList(new LambdaQueryWrapper<UmsResourceCategory>().orderByDesc(UmsResourceCategory::getSort));
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return umsResourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        return umsResourceCategoryMapper.updateById(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return umsResourceCategoryMapper.deleteById(id);
    }
}
