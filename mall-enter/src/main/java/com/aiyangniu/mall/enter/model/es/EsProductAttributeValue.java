package com.aiyangniu.mall.enter.model.es;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 搜索商品的属性信息
 *
 * @author lzq
 * @date 2023/05/13
 */
@Data
@EqualsAndHashCode
public class EsProductAttributeValue implements Serializable {

    private static final long serialVersionUID = -6782126213409221240L;

    private Long id;

    private Long productAttributeId;

    /** 属性值 **/
    @Field(type = FieldType.Keyword)
    private String value;

    /** 属性参数 0:规格 1:参数 **/
    private Integer type;

    /** 属性名称 **/
    @Field(type=FieldType.Keyword)
    private String name;
}
