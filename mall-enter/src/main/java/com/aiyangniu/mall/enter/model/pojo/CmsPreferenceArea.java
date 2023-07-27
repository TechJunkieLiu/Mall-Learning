package com.aiyangniu.mall.enter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 优选专区实体
 *
 * @author lzq
 * @date 2023/06/01
 */
@Data
public class CmsPreferenceArea implements Serializable {

    private static final long serialVersionUID = -7589716818472063887L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "专区名称")
    private String name;

    @ApiModelProperty(value = "专区标题")
    private String subTitle;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "展示状态")
    private Integer showStatus;

    @ApiModelProperty(value = "展示图片")
    private byte[] pic;
}