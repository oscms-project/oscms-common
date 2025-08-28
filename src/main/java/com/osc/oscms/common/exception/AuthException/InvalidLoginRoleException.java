package com.osc.oscms.common.exception.AuthException;

public class InvalidLoginRoleException extends org.springframework.security.core.AuthenticationException { // 继承 AuthenticationException
    public InvalidLoginRoleException(String msg) {
        super(msg);
    }

    public InvalidLoginRoleException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
