package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.OmsOrder;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderItem;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderOperateHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息（包含商品信息）
 *
 * @author lzq
 * @date 2023/05/09
 */
@Data
public class OmsOrderDetail extends OmsOrder {

    private static final long serialVersionUID = 7195752814367945612L;

    @Getter
    @Setter
    @ApiModelProperty("订单商品列表")
    private List<OmsOrderItem> orderItemList;

    @Getter
    @Setter
    @ApiModelProperty("订单操作记录列表")
    private List<OmsOrderOperateHistory> historyList;
}
