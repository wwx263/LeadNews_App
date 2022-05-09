package com.heima.apis.admin;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * admin模块的接口,用于调用其他模块的接口
 */
@Api(value = "频道管理",tags = "channel",description = "频道管理API")
public interface AdChannelControllerApi {

    /**
     * 根据名称分页查询频道列表
     * @param dto
     * @return
     */
    @ApiOperation("频道分页列表查询")
    public ResponseResult findByNameAndPage(ChannelDto dto);

    /**
     * 保存频道内容
     * @param channel
     * @return
     */
    @ApiOperation("保存频道")
    @ApiOperationSupport(ignoreParameters = {"id","createdTime"})
    ResponseResult save (AdChannel channel);


}
