package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.PmsProductResult;
import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品管理Mapper
 *
 * @author lzq
 * @date 2023/05/25
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    /**
     * 获取商品编辑信息
     *
     * @param id 商品ID
     * @return 商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
