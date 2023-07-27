package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.SmsFlashPromotionProductRelationMapper;
import com.aiyangniu.mall.enter.model.bo.SmsFlashPromotionProduct;
import com.aiyangniu.mall.enter.model.pojo.SmsFlashPromotionProductRelation;
import com.aiyangniu.mall.enter.service.SmsFlashPromotionProductRelationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 限时购商品关联管理实现类
 *
 * @author lzq
 * @date 2023/06/15
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {

    private final SmsFlashPromotionProductRelationMapper relationMapper;

    @Override
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        for (SmsFlashPromotionProductRelation relation : relationList) {
            relationMapper.insert(relation);
        }
        return relationList.size();
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        return relationMapper.updateById(relation);
    }

    @Override
    public int delete(Long id) {
        return relationMapper.deleteById(id);
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Long id) {
        return relationMapper.selectById(id);
    }

    @Override
    public List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return relationMapper.getList(flashPromotionId, flashPromotionSessionId);
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        return relationMapper.selectCount(new LambdaQueryWrapper<SmsFlashPromotionProductRelation>().eq(SmsFlashPromotionProductRelation::getFlashPromotionId, flashPromotionId).eq(SmsFlashPromotionProductRelation::getFlashPromotionSessionId, flashPromotionSessionId));
    }
}
