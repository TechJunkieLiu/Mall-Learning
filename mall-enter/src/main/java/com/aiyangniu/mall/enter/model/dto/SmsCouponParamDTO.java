package com.aiyangniu.mall.enter.model.dto;

import com.aiyangniu.mall.enter.model.pojo.SmsCoupon;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponProductCategoryRelation;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠券信息封装，包括绑定商品和分类
 *
 * @author lzq
 * @date 2023/06/01
 */
public class SmsCouponParamDTO extends SmsCoupon {

    private static final long serialVersionUID = 33118688027578785L;

    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品")
    private List<SmsCouponProductRelation> productRelationList;

    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;

    @Override
    public String toString() {
        return "SmsCouponParamDTO{" +
                "productRelationList=" + productRelationList +
                ", productCategoryRelationList=" + productCategoryRelationList +
                '}';
    }
}
