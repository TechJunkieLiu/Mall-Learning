package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.SmsCouponProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券关联商品Mapper
 *
 * @author lzq
 * @date 2023/05/31
 */
public interface SmsCouponProductRelationMapper extends BaseMapper<SmsCouponProductRelation> {

    /**
     * 批量创建
     *
     * @param productRelationList 优惠券和产品关系列表
     * @return 创建个数
     */
    int insertList(@Param("list") List<SmsCouponProductRelation> productRelationList);
}
