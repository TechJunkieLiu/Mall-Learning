package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 专题和商品关系实体
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
public class CmsSubjectProductRelation implements Serializable {

    private static final long serialVersionUID = 943612161588361346L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "专题ID")
    private Long subjectId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;
}