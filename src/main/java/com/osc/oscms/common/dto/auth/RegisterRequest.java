package com.osc.oscms.common.dto.auth;

import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 用户注册请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
public class RegisterRequest {

    /**
     * 用户ID（学号/工号）
     */
    @NotBlank(message = "用户ID不能为空")
    @Size(max = 50, message = "用户ID长度不能超过50个字符")
    private String userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 50, message = "用户名长度必须在2到50个字符之间")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 100, message = "密码长度必须在6到100个字符之间")
    private String password;

    /**
     * 用户角色
     */
    @NotBlank(message = "用户角色不能为空")
    @Pattern(regexp = "^(teacher|ta|student)$", message = "用户角色必须是teacher、ta或student")
    private String role;

    /**
     * 邮箱地址（可选）
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;
}
