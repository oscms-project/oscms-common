package com.osc.oscms.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用 API 响应体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    /** 业务状态码，这与HTTP状态码不同 */
    private int code;
    /** 说明信息 */
    private String message;
    /** 返回的数据主体 */
    private T data;

    /** 快捷构造成功响应 */
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    /** 快捷构造失败响应 */
    public static <T> ApiResponse<T> fail(int code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }
}




