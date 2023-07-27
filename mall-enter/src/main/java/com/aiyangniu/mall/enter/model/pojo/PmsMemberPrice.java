package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品会员价格实体
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
public class PmsMemberPrice implements Serializable {

    private static final long serialVersionUID = 3015553888467763120L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "会员等级ID")
    private Long memberLevelId;

    @ApiModelProperty(value = "会员价格")
    private BigDecimal memberPrice;

    @ApiModelProperty(value = "会员等级名称")
    private String memberLevelName;
}