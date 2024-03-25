package com.xxz.exception;

import com.xxz.Pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
        public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起 请联系管理员");
    }
}
