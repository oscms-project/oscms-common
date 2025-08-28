package com.osc.oscms.common.exception;

/**
 * 业务异常基类
 */
public class BusinessException extends BaseException {

    public BusinessException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}




