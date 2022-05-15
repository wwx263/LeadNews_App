package com.heima.common.exception;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
/**
 * 全局异常类,用于捕捉错误
 */
public class ExceptionCatch {

    //异常类注解
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception){
        exception.printStackTrace();
        //记录日志
        log.error("catch exception:{}",exception.getMessage());
        //返回通用异常
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"系统正在维护,请稍后重试");
    }
}
