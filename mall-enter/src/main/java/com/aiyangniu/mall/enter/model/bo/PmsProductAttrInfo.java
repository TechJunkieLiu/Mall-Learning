package com.aiyangniu.mall.enter.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类对应属性信息
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
@EqualsAndHashCode
public class PmsProductAttrInfo {

    @ApiModelProperty("商品属性ID")
    private Long attributeId;

    @ApiModelProperty("商品属性分类ID")
    private Long attributeCategoryId;
}
