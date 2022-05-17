package com.heima.user.controller;

import com.heima.apis.user.ApUserRealnameControllerApi;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.AuthDto;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController implements ApUserRealnameControllerApi {

    @Autowired
    private ApUserRealnameService apUserRealnameService;
    /**
     * 根据状态查询需要认证相关的用户信息
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Override
    public PageResponseResult loadListByStatus(@RequestBody AuthDto dto) {
        if (dto == null){
            return (PageResponseResult) PageResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"参数不能为空");
        }
        return apUserRealnameService.loadListByStatus(dto);
    }
}
