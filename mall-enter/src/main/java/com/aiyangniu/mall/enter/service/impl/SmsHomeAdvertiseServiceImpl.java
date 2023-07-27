package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.enter.mapper.SmsHomeAdvertiseMapper;
import com.aiyangniu.mall.enter.model.pojo.SmsHomeAdvertise;
import com.aiyangniu.mall.enter.service.SmsHomeAdvertiseService;
import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 首页轮播广告管理实现类
 *
 * @author lzq
 * @date 2023/06/08
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {

    private final SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;

    @Override
    public int create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return smsHomeAdvertiseMapper.insert(advertise);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeAdvertiseMapper.delete(new LambdaQueryWrapper<SmsHomeAdvertise>().in(SmsHomeAdvertise::getId, ids));
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise record = new SmsHomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        return smsHomeAdvertiseMapper.updateById(record);
    }

    @Override
    public SmsHomeAdvertise getItem(Long id) {
        return smsHomeAdvertiseMapper.selectById(id);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        return smsHomeAdvertiseMapper.updateById(advertise);
    }

    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return smsHomeAdvertiseMapper.selectList(new LambdaQueryWrapper<SmsHomeAdvertise>()
                .like(!StrUtil.isEmpty(name), SmsHomeAdvertise::getName, name)
                .eq(type != null, SmsHomeAdvertise::getType, type)
                .between(!StrUtil.isEmpty(endTime),  SmsHomeAdvertise::getEndTime, DateUtil.parse(!StrUtil.isEmpty(endTime) ? endTime : LocalDate.now() + " 00:00:00", DatePattern.NORM_DATETIME_PATTERN), DateUtil.parse(!StrUtil.isEmpty(endTime) ? endTime : LocalDate.now() + " 23:59:59", DatePattern.NORM_DATETIME_PATTERN))
                .orderByDesc(SmsHomeAdvertise::getSort)
        );
    }
}
