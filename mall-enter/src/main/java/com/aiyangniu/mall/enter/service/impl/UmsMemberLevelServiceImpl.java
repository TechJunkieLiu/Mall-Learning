package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.UmsMemberLevelMapper;
import com.aiyangniu.mall.enter.model.pojo.UmsMemberLevel;
import com.aiyangniu.mall.enter.service.UmsMemberLevelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级管理实现类
 *
 * @author lzq
 * @date 2023/06/15
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {

    private final UmsMemberLevelMapper memberLevelMapper;

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        return memberLevelMapper.selectList(new LambdaQueryWrapper<UmsMemberLevel>().eq(UmsMemberLevel::getDefaultStatus, defaultStatus));
    }
}
