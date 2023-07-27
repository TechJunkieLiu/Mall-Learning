package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.PmsMemberPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品会员价格Mapper
 *
 * @author lzq
 * @date 2023/05/30
 */
public interface PmsMemberPriceMapper extends BaseMapper<PmsMemberPrice> {

    /**
     * 批量创建
     *
     * @param memberPriceList 商品会员价格列表
     * @return 创建个数
     */
    int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}
