package com.heima.wemedia.controller.v1;

import com.heima.apis.wemedia.WmUserControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.pojos.WmUser;
import com.heima.wemedia.service.WmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
/**
 * 该接口用于访问用户的自媒体频道
 */
public class WmUserController implements WmUserControllerApi {
    @Autowired
    private WmUserService wmUserService;

    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody WmUser wmUser) {
        if (wmUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "不能为空");
        }
        wmUserService.insert(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @GetMapping("/findByName/{name}")
    @Override
    public ResponseResult findByName(@PathVariable("name") String name) {
        ResponseResult<WmUser> result = new ResponseResult<>();

        if (StringUtils.isEmpty(name)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道名称不能为空");
        }
        WmUser byName = wmUserService.findByName(name);

        if (byName != null) {
            result.setData(byName);
            return result;
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "不存在该频道");
    }
}
