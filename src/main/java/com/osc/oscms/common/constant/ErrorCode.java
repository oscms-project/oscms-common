package com.osc.oscms.common.constant;

/**
 * 错误代码常量
 */
public class ErrorCode {
    // 通用错误
    public static final String SUCCESS = "0000";
    public static final String SYSTEM_ERROR = "9999";
    public static final String PARAM_ERROR = "1001";

    // 用户相关错误
    public static final String USER_NOT_FOUND = "2001";
    public static final String USER_ALREADY_EXISTS = "2002";
    public static final String INVALID_CREDENTIALS = "2003";
    public static final String ACCESS_DENIED = "2004";

    // 课程相关错误
    public static final String COURSE_NOT_FOUND = "3001";
    public static final String COURSE_ALREADY_EXISTS = "3002";

    // 班级相关错误
    public static final String CLASS_NOT_FOUND = "4001";
    public static final String STUDENT_NOT_ENROLLED = "4002";

    // 作业相关错误
    public static final String ASSIGNMENT_NOT_FOUND = "5001";
    public static final String SUBMISSION_DEADLINE_EXCEEDED = "5002";

    private ErrorCode() {
        // 防止实例化
    }
}




