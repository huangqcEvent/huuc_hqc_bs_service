package com.huuc.handler;

import com.huuc.exception.BaseException;
import com.huuc.result.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     */
    @ExceptionHandler
    public AjaxResult exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return AjaxResult.error(ex.getMessage());
    }

    @ExceptionHandler
    public AjaxResult exceptionHandler(FileSizeLimitExceededException SizeException){
        log.error("异常信息:文件过大");
        return AjaxResult.error("文件太大了");
    }
}
