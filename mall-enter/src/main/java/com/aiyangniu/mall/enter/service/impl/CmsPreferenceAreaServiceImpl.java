package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.CmsPreferenceAreaMapper;
import com.aiyangniu.mall.enter.model.pojo.CmsPreferenceArea;
import com.aiyangniu.mall.enter.service.CmsPreferenceAreaService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选管理实现类
 *
 * @author lzq
 * @date 2023/06/01
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CmsPreferenceAreaServiceImpl implements CmsPreferenceAreaService {

    private final CmsPreferenceAreaMapper cmsPreferenceAreaMapper;

    @Override
    public List<CmsPreferenceArea> listAll() {
        return cmsPreferenceAreaMapper.selectList(new LambdaQueryWrapper<>());
    }
}
