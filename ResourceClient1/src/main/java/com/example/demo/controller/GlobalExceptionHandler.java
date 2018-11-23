package com.example.demo.controller;

import com.example.demo.common.APIResult;
import com.example.demo.common.HTTPCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description: 全局Exception 捕获处理函数
 * @Author: liumeng
 * @Date: 2018/10/29 16:05
 * @Param:
 * @Return:
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
public APIResult defaultErrorHandler(Exception ex){

    APIResult apiResult = null;
    if (ex instanceof AccessDeniedException) {
        //不满足权限的异常
        apiResult = new APIResult(HTTPCode.UNAUTHORIZED, ex.getMessage(), false);
    }else{
        apiResult = new APIResult(HTTPCode.SERVER_ERR, ex.getMessage(), false);
    }
    LOGGER.error(apiResult.getMsg(), ex);
    return apiResult;
}
}
