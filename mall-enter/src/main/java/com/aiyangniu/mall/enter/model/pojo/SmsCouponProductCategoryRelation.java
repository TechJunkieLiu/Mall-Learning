package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券和产品分类关系的实体
 *
 * @author lzq
 * @date 2023/05/24
 */
@Data
public class SmsCouponProductCategoryRelation implements Serializable {

    private static final long serialVersionUID = 4612496951562044872L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优惠券ID")
    private Long couponId;

    @ApiModelProperty(value = "产品分类ID")
    private Long productCategoryId;

    @ApiModelProperty(value = "产品分类名称")
    private String productCategoryName;

    @ApiModelProperty(value = "父分类名称")
    private String parentCategoryName;
}