package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页推荐专题实体
 *
 * @author lzq
 * @date 2023/06/21
 */
@Data
public class SmsHomeRecommendSubject implements Serializable {

    private static final long serialVersionUID = 123703152080288251L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "专题ID")
    private Long subjectId;

    @ApiModelProperty(value = "专题名称")
    private String subjectName;

    @ApiModelProperty(value = "推荐状态")
    private Integer recommendStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}