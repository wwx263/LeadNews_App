package com.heima.model.admin.dtos;


import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 模块之间互相传送的敏感词数据dto
 *
 */
@Data
public class SensitiveDto extends PageRequestDto {
    /**
     * 敏感词名称
     */
    @ApiModelProperty("敏感词")
    private String name;
}
