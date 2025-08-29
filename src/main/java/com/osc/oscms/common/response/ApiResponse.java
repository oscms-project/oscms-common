package com.osc.oscms.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用API响应模型
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    /**
     * 业务状态码（通常与HTTP状态码一致）
     */
    private Integer code;

    /**
     * 说明信息
     */
    private String message;

    /**
     * 返回的数据主体（具体类型根据接口而定）
     */
    private T data;

    /**
     * 成功响应（无数据）
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "success", null);
    }

    /**
     * 成功响应（有数据）
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    /**
     * 成功响应（自定义消息）
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    /**
     * 失败响应
     */
    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    /**
     * 失败响应（400错误）
     */
    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(400, message, null);
    }

    /**
     * 失败响应（401错误）
     */
    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(401, message, null);
    }

    /**
     * 失败响应（403错误）
     */
    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(403, message, null);
    }

    /**
     * 失败响应（404错误）
     */
    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message, null);
    }

    /**
     * 失败响应（500错误）
     */
    public static <T> ApiResponse<T> internalError(String message) {
        return new ApiResponse<>(500, message, null);
    }

    /**
     * 判断响应是否成功
     */
    public boolean isSuccess() {
        return code != null && code >= 200 && code < 300;
    }

    // 别名方法，与控制器中使用的方法名保持一致
    public static <T> ApiResponse<T> ok() {
        return success();
    }

    public static <T> ApiResponse<T> ok(T data) {
        return success(data);
    }

    public static <T> ApiResponse<T> fail(Integer code, String message) {
        return error(code, message);
    }
}