package com.osc.oscms.common.exception.AuthException;

public class UserIdAlreadyExistsException extends RuntimeException { // 直接继承 RuntimeException

    public UserIdAlreadyExistsException(String message) {
        super(message);
    }

    public UserIdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}