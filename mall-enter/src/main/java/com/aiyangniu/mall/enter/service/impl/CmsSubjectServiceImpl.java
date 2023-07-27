package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.enter.mapper.CmsSubjectMapper;
import com.aiyangniu.mall.enter.model.pojo.CmsSubject;
import com.aiyangniu.mall.enter.service.CmsSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品专题管理实现类
 *
 * @author lzq
 * @date 2023/06/01
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CmsSubjectServiceImpl implements CmsSubjectService {

    private final CmsSubjectMapper cmsSubjectMapper;

    @Override
    public List<CmsSubject> listAll() {
        return cmsSubjectMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return cmsSubjectMapper.selectList(new LambdaQueryWrapper<CmsSubject>().like(!StrUtil.isEmpty(keyword), CmsSubject::getTitle, keyword));
    }
}
