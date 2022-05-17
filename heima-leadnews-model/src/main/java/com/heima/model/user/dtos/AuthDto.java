package com.heima.model.user.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthDto extends PageRequestDto {
    @ApiModelProperty("id")
    private Integer id;
    //驳回的信息
    @ApiModelProperty("驳回的信息")
    private String msg;
    //状态
    @ApiModelProperty("状态")
    private Short status;
}
