package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.CmsSubjectProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专题和商品关系Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface CmsSubjectProductRelationMapper extends BaseMapper<CmsSubjectProductRelation> {

    /**
     * 批量创建
     *
     * @param subjectProductRelationList 专题和商品关系列表
     * @return 创建个数
     */
    int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}
