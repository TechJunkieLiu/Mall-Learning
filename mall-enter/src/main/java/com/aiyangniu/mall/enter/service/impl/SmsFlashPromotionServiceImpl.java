package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.enter.mapper.SmsFlashPromotionMapper;
import com.aiyangniu.mall.enter.model.pojo.SmsFlashPromotion;
import com.aiyangniu.mall.enter.service.SmsFlashPromotionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 限时购活动管理实现类
 *
 * @author lzq
 * @date 2023/06/15
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {

    private final SmsFlashPromotionMapper flashPromotionMapper;

    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(new Date());
        return flashPromotionMapper.insert(flashPromotion);
    }

    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        return flashPromotionMapper.updateById(flashPromotion);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionMapper.deleteById(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
        return flashPromotionMapper.updateById(flashPromotion);
    }

    @Override
    public SmsFlashPromotion getItem(Long id) {
        return flashPromotionMapper.selectById(id);
    }

    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return flashPromotionMapper.selectList(new LambdaQueryWrapper<SmsFlashPromotion>().like(!StrUtil.isEmpty(keyword), SmsFlashPromotion::getTitle, keyword));
    }
}
