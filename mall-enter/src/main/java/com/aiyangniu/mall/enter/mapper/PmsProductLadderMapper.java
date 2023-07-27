package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.PmsProductLadder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品阶梯价格Mapper
 *
 * @author lzq
 * @date 2023/05/25
 */
public interface PmsProductLadderMapper extends BaseMapper<PmsProductLadder> {

    /**
     * 批量创建
     *
     * @param productLadderList 商品阶梯价格列表
     * @return 创建个数
     */
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
