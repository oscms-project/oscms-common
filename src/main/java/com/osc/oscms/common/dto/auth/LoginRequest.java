package com.osc.oscms.common.dto.auth;

import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 用户登录请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
public class LoginRequest {

    /**
     * 用户ID（学号/工号）
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 登录角色
     */
    @NotBlank(message = "登录角色不能为空")
    @Pattern(regexp = "^(teacher|ta|student)$", message = "登录角色必须是teacher、ta或student")
    private String role;
}
