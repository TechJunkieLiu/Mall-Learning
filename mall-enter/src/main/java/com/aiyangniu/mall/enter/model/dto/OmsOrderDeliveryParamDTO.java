package com.aiyangniu.mall.enter.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单发货参数
 *
 * @author lzq
 * @date 2023/06/01
 */
@Getter
@Setter
public class OmsOrderDeliveryParamDTO {

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("物流公司")
    private String deliveryCompany;

    @ApiModelProperty("物流单号")
    private String deliverySn;

    @Override
    public String toString() {
        return "OmsOrderDeliveryParamDTO{" +
                "orderId=" + orderId +
                ", deliveryCompany='" + deliveryCompany + '\'' +
                ", deliverySn='" + deliverySn + '\'' +
                '}';
    }

}
