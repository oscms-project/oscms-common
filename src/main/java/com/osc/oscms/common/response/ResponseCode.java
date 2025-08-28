package com.osc.oscms.common.response;

/**
 * 响应状态码常量
 */
public class ResponseCode {
    public static final String SUCCESS = "SUCCESS";
    public static final String OPERATION_FAILED = "OPERATION_FAILED";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String INVALID_PARAM = "INVALID_PARAM";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String DUPLICATE_DATA = "DUPLICATE_DATA";
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";

    private ResponseCode() {
        // 防止实例化
    }
}



