package com.huuc.exception;
/**
 *
 * @author hqc
 * 业务异常处理类
 *
 */
public class BaseException extends RuntimeException{
    public BaseException(){}
    public BaseException(String message){
        super(message);
    }
}
