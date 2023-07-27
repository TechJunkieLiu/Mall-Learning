package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.dto.PmsProductParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询单个商品修改后返回的结果
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
public class PmsProductResult extends PmsProductParamDTO {

    private static final long serialVersionUID = -8079604995505421220L;

    @ApiModelProperty("商品所选分类的父id")
    private Long cateParentId;
}
