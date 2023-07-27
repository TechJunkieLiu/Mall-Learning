package com.aiyangniu.mall.enter.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传返回结果
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
@EqualsAndHashCode
public class MinioUpload {

    @ApiModelProperty("文件访问URL")
    private String url;

    @ApiModelProperty("文件名称")
    private String name;
}
