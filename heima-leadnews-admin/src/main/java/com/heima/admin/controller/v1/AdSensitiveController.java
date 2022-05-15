package com.heima.admin.controller.v1;

import com.heima.admin.service.AdSensitiveService;
import com.heima.apis.admin.AdSensitiveControllerApi;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sensitive")
public class AdSensitiveController implements AdSensitiveControllerApi {

    @Autowired
    private AdSensitiveService adSensitiveService;

    /**
     * 模糊查询
     *
     * @param dto
     * @return
     */
    @Override
    @PostMapping("/list")
    public ResponseResult list(@RequestBody SensitiveDto dto) {
        if (null == dto) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return adSensitiveService.list(dto);
    }

    /**
     * 保存敏感词
     *
     * @param adSensitive
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody AdSensitive adSensitive) {
        if (null == adSensitive) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return adSensitiveService.insert(adSensitive);
    }

    /**
     * 更新敏感词
     *
     * @param adSensitive
     * @return
     */
    @PostMapping("/update")
    @Override
    public ResponseResult update(@RequestBody AdSensitive adSensitive) {
        if (null == adSensitive) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return adSensitiveService.update(adSensitive);
    }

    /**
     * 删除敏感词
     *
     * @param id
     * @return
     */
    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        if (null == id) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return adSensitiveService.deleteById(id);
    }
}
