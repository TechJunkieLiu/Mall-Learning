package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.enter.mapper.UmsResourceMapper;
import com.aiyangniu.mall.enter.model.pojo.UmsResource;
import com.aiyangniu.mall.enter.service.UmsAdminCacheService;
import com.aiyangniu.mall.enter.service.UmsResourceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源管理实现类
 *
 * @author lzq
 * @date 2023/05/26
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsResourceServiceImpl implements UmsResourceService {

    private final UmsResourceMapper umsResourceMapper;
    private final UmsAdminCacheService umsAdminCacheService;

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return umsResourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        int count = umsResourceMapper.updateById(umsResource);
        umsAdminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public UmsResource getItem(Long id) {
        return umsResourceMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        int count = umsResourceMapper.deleteById(id);
        umsAdminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return umsResourceMapper.selectList(new LambdaQueryWrapper<UmsResource>()
                .eq(categoryId != null, UmsResource::getCategoryId, categoryId)
                .like(StrUtil.isNotEmpty(nameKeyword), UmsResource::getName, nameKeyword)
                .like(StrUtil.isNotEmpty(urlKeyword), UmsResource::getUrl, urlKeyword)
        );
    }

    @Override
    public List<UmsResource> listAll() {
        return umsResourceMapper.selectList(new LambdaQueryWrapper<>());
    }
}
