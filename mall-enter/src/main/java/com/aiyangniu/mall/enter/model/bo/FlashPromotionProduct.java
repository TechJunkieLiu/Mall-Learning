package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 秒杀信息和商品对象封装
 *
 * @author lzq
 * @date 2023/06/08
 */
@Getter
@Setter
public class FlashPromotionProduct extends PmsProduct {

    private static final long serialVersionUID = -5450426011673856948L;

    @ApiModelProperty("秒杀价格")
    private BigDecimal flashPromotionPrice;

    @ApiModelProperty("用于秒杀到数量")
    private Integer flashPromotionCount;

    @ApiModelProperty("秒杀限购数量")
    private Integer flashPromotionLimit;
}
