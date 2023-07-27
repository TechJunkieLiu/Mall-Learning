package com.aiyangniu.mall.enter.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改用户名密码参数
 *
 * @author lzq
 * @date 2023/05/08
 */
@Data
public class UpdateAdminPasswordDTO {

    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;

    @NotBlank
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
