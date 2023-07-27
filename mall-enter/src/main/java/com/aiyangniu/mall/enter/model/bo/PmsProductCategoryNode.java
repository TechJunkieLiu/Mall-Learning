package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含子分类的商品分类
 *
 * @author lzq
 * @date 2023/07/21
 */
@Getter
@Setter
public class PmsProductCategoryNode extends PmsProductCategory {

    private static final long serialVersionUID = 2335528293069839573L;

    @ApiModelProperty("子分类集合")
    private List<PmsProductCategoryNode> children;
}
