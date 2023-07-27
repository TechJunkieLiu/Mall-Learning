package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.PmsProductAttribute;
import com.aiyangniu.mall.enter.model.pojo.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 带有属性的商品属性分类
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {

    private static final long serialVersionUID = -8189850916110593516L;

    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}
