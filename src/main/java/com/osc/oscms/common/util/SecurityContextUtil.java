package com.osc.oscms.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 安全上下文工具类
 * 用于从Spring Security上下文中获取当前用户信息
 */
@Component
public class SecurityContextUtil {

    /**
     * 获取当前认证用户的ID
     * 
     * @return 用户ID，如果未认证则返回null
     */
    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else if (principal instanceof String) {
                return (String) principal;
            }
        }
        return null;
    }

    /**
     * 获取当前认证用户的角色列表
     * 
     * @return 角色列表，如果未认证则返回null
     */
    public java.util.List<String> getCurrentUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .map(Object::toString)
                    .collect(java.util.stream.Collectors.toList());
        }
        return null;
    }

    /**
     * 检查当前用户是否已认证
     * 
     * @return 如果已认证返回true，否则返回false
     */
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * 检查当前用户是否具有指定角色
     * 
     * @param role 要检查的角色
     * @return 如果具有该角色返回true，否则返回false
     */
    public boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals(role));
        }
        return false;
    }
}
