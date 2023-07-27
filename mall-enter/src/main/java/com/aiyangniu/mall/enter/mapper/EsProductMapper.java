package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.es.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索商品管理Mapper
 *
 * @author lzq
 * @date 2023/05/13
 */
public interface EsProductMapper {

    /**
     * 获取指定ID的搜索商品
     *
     * @param id 商品ID
     * @return 商品信息
     */
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
