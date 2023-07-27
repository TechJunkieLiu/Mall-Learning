package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单商品信息管理Mapper
 *
 * @author lzq
 * @date 2023/05/25
 */
public interface EnterOrderItemMapper {

    /**
     * 批量插入
     *
     * @param list 订单列表（包含商品实体）
     * @return 插入数量
     */
    int insertList(@Param("list") List<OmsOrderItem> list);
}
