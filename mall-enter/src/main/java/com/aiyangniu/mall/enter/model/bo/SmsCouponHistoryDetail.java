package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.SmsCoupon;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponHistory;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponProductCategoryRelation;
import com.aiyangniu.mall.enter.model.pojo.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠券领取历史详情（包括优惠券信息和关联关系）
 *
 * @author lzq
 * @date 2023/05/22
 */
@Getter
@Setter
public class SmsCouponHistoryDetail extends SmsCouponHistory {

    private static final long serialVersionUID = -2232835408782793544L;

    @ApiModelProperty("相关优惠券信息")
    private SmsCoupon coupon;

    @ApiModelProperty("优惠券关联商品")
    private List<SmsCouponProductRelation> productRelationList;

    @ApiModelProperty("优惠券关联商品分类")
    private List<SmsCouponProductCategoryRelation> categoryRelationList;
}
