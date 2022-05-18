package com.heima.user.controller.v1;

import com.heima.apis.user.ApUserRealnameControllerApi;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
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
/**
 * 该接口用于查询用户申请实名制的情况,例如还未通过审核的频道申请
 */
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
    /**
     * 审核通过
     * @param dto
     * @return
     */
    @Override
    public ResponseResult authPass(AuthDto dto) {
        return null;
    }
    /**
     * 审核失败
     * @param dto
     * @return
     */
    @Override
    public ResponseResult authFail(AuthDto dto) {
        return null;
    }
}
