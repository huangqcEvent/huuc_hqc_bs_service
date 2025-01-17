package com.huuc.exception;

/**
 * 密码错误异常
 */
public class ResponseException extends BaseException {

    public ResponseException() {
    }

    public ResponseException(String msg) {
        super(msg);
    }

}
