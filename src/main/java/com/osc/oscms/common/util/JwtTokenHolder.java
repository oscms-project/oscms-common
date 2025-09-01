package com.osc.oscms.common.util;

/**
 * Utility class to hold JWT tokens in ThreadLocal for inter-service
 * communication
 */
public class JwtTokenHolder {
    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    public static void setCurrentToken(String token) {
        tokenHolder.set(token);
    }

    public static String getCurrentToken() {
        return tokenHolder.get();
    }

    public static void clear() {
        tokenHolder.remove();
    }
}
