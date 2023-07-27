package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.SmsHomeRecommendProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页人气推荐管理Mapper
 *
 * @author lzq
 * @date 2023/06/25
 */
public interface SmsHomeRecommendProductMapper extends BaseMapper<SmsHomeRecommendProduct> {

    /**
     * 批量添加首页人气推荐
     *
     * @param list 首页人气推荐列表
     * @return 添加数量
     */
    int insertList(@Param("list") List<SmsHomeRecommendProduct> list);
}
