package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.SmsHomeNewProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页新品推荐管理Mapper
 *
 * @author lzq
 * @date 2023/06/25
 */
public interface SmsHomeNewProductMapper extends BaseMapper<SmsHomeNewProduct> {

    /**
     * 批量添加首页新品推荐
     *
     * @param list 首页新品推荐列表
     * @return 添加数量
     */
    int insertList(@Param("list") List<SmsHomeNewProduct> list);
}
