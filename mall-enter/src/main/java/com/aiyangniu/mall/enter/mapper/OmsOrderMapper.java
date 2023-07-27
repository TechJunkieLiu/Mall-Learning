package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.OmsOrderDetail;
import com.aiyangniu.mall.enter.model.dto.OmsOrderDeliveryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsOrderQueryParamDTO;
import com.aiyangniu.mall.enter.model.pojo.OmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台订单管理Mapper
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    /**
     * 条件查询订单
     *
     * @param queryParam 查询条件
     * @return 订单列表
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParamDTO queryParam);

    /**
     * 批量发货
     *
     * @param deliveryParamList 订单发货参数
     * @return 发货数量
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParamDTO> deliveryParamList);

    /**
     * 获取订单详情
     *
     * @param id 订单ID
     * @return 订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);
}
