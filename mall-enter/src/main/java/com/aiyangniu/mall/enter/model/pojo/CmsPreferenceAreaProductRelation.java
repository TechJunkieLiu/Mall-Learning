package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 优选专区和商品的关系实体
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
public class CmsPreferenceAreaProductRelation implements Serializable {

    private static final long serialVersionUID = -8156099590373422715L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优选专区ID")
    private Long preferenceAreaId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;
}