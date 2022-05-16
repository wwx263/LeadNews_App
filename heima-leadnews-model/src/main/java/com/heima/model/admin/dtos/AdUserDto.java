package com.heima.model.admin.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 模块之间传递的用户dto
 */
@Data
public class AdUserDto {
    //用户名
    @ApiModelProperty("用户名")
    private String name;

    //密码
   @ApiModelProperty("密码")
   private String password;
}
