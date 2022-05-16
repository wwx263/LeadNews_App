package com.heima.admin.controller.v1;

import com.heima.admin.service.UserLoginService;
import com.heima.apis.admin.LoginControllerApi;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginControllerApi {
    @Autowired
    private UserLoginService loginService;

    /**
     * 用户登录
     *
     * @param dto
     * @return
     */
    @PostMapping("/in")
    @Override
    public ResponseResult login(@RequestBody AdUserDto dto) {
        if (StringUtils.isEmpty(dto.getName()) ||StringUtils.isEmpty(dto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE,"密码或者账号不能为空");
        }
            return loginService.login(dto);
    }
}
