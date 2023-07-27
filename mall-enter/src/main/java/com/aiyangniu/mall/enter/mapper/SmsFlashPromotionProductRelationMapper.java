package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.SmsFlashPromotionProduct;
import com.aiyangniu.mall.enter.model.pojo.SmsFlashPromotionProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 限时购商品关联Mapper
 *
 * @author lzq
 * @date 2023/06/15
 */
public interface SmsFlashPromotionProductRelationMapper extends BaseMapper<SmsFlashPromotionProductRelation> {

    /**
     * 获取限时购及相关商品信息
     *
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     * @return 限时购商品信息列表
     */
    List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
