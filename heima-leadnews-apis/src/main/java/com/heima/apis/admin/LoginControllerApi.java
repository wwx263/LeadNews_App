package com.heima.apis.admin;

import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户登录
 */
@Api(value = "用户登录",tags = "login",description = "用户登录api")
public interface LoginControllerApi {
    /**
     * admin登录功能
     * @param dto
     * @return
     */
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody AdUserDto dto);
}
