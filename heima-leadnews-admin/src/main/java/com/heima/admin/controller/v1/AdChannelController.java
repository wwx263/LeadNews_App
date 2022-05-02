package com.heima.admin.controller.v1;

import com.heima.admin.service.AdChannelService;
import com.heima.apis.admin.AdChannelControllerApi;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/channel")
public class AdChannelController implements AdChannelControllerApi {
    /*频道服务 在admin模块下,调用自己包下服务使用autowired 远程则用 reference*/
    @Autowired
    private AdChannelService adChannelService;

    /**
     * 根据名称模糊查询对应结果
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        if (null == dto){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return adChannelService.findByNameAndPage(dto);
    }

}
