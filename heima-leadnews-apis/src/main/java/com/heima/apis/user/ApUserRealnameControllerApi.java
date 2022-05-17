package com.heima.apis.user;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户认证列表查询",tags = "ApUserRealname",description = "用户认证列表查询API")
public interface ApUserRealnameControllerApi {
    /**
     *按照状态查询用户认证列表
     * @param dto
     * @return
     */
    @ApiOperation("用户认证列表查询")
    public ResponseResult loadListByStatus(AuthDto dto);
}
