package com.osc.oscms.common.exception.CourseException;

/**
 * 当未找到用户在课程/班级中的注册、选课或加入记录时抛出此异常。
 */
public class EnrollmentNotFoundException extends RuntimeException {

    public EnrollmentNotFoundException(String message) {
        super(message);
    }

    public EnrollmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // 可以添加更具体的构造函数，例如
    public EnrollmentNotFoundException(String studentId, Long courseId) {
        super("学生ID '" + studentId + "' 未在课程ID '" + courseId + "' 中找到注册/选课记录。");
    }

    public EnrollmentNotFoundException(String studentId, Long classId, boolean isForClass) {
        super("学生ID '" + studentId + "' 未在班级ID '" + classId + "' 中找到注册/选课记录。");
    }
}
