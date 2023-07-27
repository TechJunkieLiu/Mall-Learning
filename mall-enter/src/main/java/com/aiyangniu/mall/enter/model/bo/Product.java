package com.aiyangniu.mall.enter.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品
 *
 * @author lzq
 * @date 2023/06/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product {

    private Long id;
    private String productSn;
    private String name;
    private String subTitle;
    private String brandName;
    private BigDecimal price;
    private Integer count;
}
