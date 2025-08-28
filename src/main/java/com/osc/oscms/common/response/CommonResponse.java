package com.osc.oscms.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;
    private boolean success;

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(200, "操作成功", null, true);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "操作成功", data, true);
    }

    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>(200, message, data, true);
    }

    public static <T> CommonResponse<T> error(String message) {
        return new CommonResponse<>(500, message, null, false);
    }

    public static <T> CommonResponse<T> error(int code, String message) {
        return new CommonResponse<>(code, message, null, false);
    }
}



