package com.osc.oscms.common.dto.user;

import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 用户信息更新DTO
 * 
 * @author OSCMS Development Team
 */
@Data
public class UserUpdate {

    /**
     * 邮箱地址（可选更新）
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;

    /**
     * 新密码（可选更新）
     */
    @Size(min = 6, max = 100, message = "新密码长度必须在6到100个字符之间")
    private String password;
}
