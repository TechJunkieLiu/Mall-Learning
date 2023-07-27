package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.PmsProductAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品参数及自定义规格属性Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface PmsProductAttributeValueMapper extends BaseMapper<PmsProductAttributeValue> {

    /**
     * 批量创建
     *
     * @param productAttributeValueList 商品参数及自定义规格属性列表
     * @return 创建个数
     */
    int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}
