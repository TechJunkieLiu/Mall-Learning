package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类和属性关系实体
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
public class PmsProductCategoryAttributeRelation implements Serializable {

    private static final long serialVersionUID = 4312518717632552482L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品分类ID")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品属性ID")
    private Long productAttributeId;
}