package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import com.aiyangniu.mall.enter.model.pojo.SmsFlashPromotionProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 限时购商品信息封装
 *
 * @author lzq
 * @date 2023/06/14
 */
@Getter
@Setter
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {

    private static final long serialVersionUID = -3246807139476959248L;

    @ApiModelProperty("关联商品")
    private PmsProduct product;
}
