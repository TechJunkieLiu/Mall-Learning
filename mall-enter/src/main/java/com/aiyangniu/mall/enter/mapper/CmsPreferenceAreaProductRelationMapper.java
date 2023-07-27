package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.CmsPreferenceAreaProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优选专区和商品的关系Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface CmsPreferenceAreaProductRelationMapper extends BaseMapper<CmsPreferenceAreaProductRelation> {

    /**
     * 批量创建
     *
     * @param preferenceAreaProductRelationList 优选专区和商品关系列表
     * @return 创建个数
     */
    int insertList(@Param("list") List<CmsPreferenceAreaProductRelation> preferenceAreaProductRelationList);
}
