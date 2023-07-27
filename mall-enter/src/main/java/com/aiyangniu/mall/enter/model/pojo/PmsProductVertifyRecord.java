package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品阶梯价格实体（只针对同商品）
 *
 * @author lzq
 * @date 2023/05/31
 */
@Data
public class PmsProductVertifyRecord implements Serializable {

    private static final long serialVersionUID = -208784732619440896L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "审核人")
    private String vertifyMan;

    private Integer status;

    @ApiModelProperty(value = "反馈详情")
    private String detail;
}