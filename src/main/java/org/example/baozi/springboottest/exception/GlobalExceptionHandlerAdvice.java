package org.example.baozi.springboottest.exception;

import org.example.baozi.springboottest.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 加入这个异常处理类是为了返回统一的异常处理
@RestControllerAdvice // 统一处理
public class GlobalExceptionHandlerAdvice {

    Logger log= LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler(Exception.class) // 明确什么异常的处理
    public ResponseMessage handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {

        log.error("Error:",e);
        return new ResponseMessage(500,null,"error");

    }
}
