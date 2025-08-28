package com.osc.oscms.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用 API 响应体
 */
@Data
@NoArgsConstructor
public class ApiResponse<T> {
    /** 业务状态码，这与HTTP状态码不同 */
    private int code;
    /** 说明信息 */
    private String message;
    /** 返回的数据主体 */
    private T data;

    /** 全参构造器 */
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /** 快捷构造成功响应 */
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<T>(200, "success", data);
    }

    /** 快捷构造成功响应（无数据） */
    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<T>(200, "success", null);
    }

    /** 快捷构造失败响应 */
    public static <T> ApiResponse<T> fail(int code, String msg) {
        return new ApiResponse<T>(code, msg, null);
    }
    
    /** 快捷构造错误响应 */
    public static <T> ApiResponse<T> error(int code, String msg) {
        return new ApiResponse<T>(code, msg, null);
    }
    
    /** 判断是否成功 */
    public boolean isSuccess() {
        return this.code == 200;
    }
}