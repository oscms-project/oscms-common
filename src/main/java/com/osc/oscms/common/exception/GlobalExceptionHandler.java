package com.osc.oscms.common.exception;

import com.osc.oscms.common.exception.AssignmentException.AssignmentNotFoundException;
import com.osc.oscms.common.exception.AuthException.EmailAlreadyExistsException;
import com.osc.oscms.common.exception.AuthException.RegistrationException;
import com.osc.oscms.common.exception.AuthException.UsernameAlreadyExistsException;
import com.osc.oscms.common.exception.AuthException.UserIdAlreadyExistsException;
import com.osc.oscms.common.exception.MaterialException.MaterialNotFoundException;
import com.osc.oscms.common.exception.QuestionException.FavoriteAlreadyExistException;
import com.osc.oscms.common.exception.QuestionException.QuestionNotFoundException;
import com.osc.oscms.common.exception.StorageException.FileStorageException;
import com.osc.oscms.common.exception.SubmissionException.*;
import com.osc.oscms.common.exception.UsersManageException.InvalidRoleException;
import com.osc.oscms.common.exception.UsersManageException.UserNotFoundException;
import com.osc.oscms.common.exception.ClassException.*;
import com.osc.oscms.common.exception.CourseException.CourseCodeAlreadyExistsException;
import com.osc.oscms.common.exception.CourseException.CourseNotFoundException;
import com.osc.oscms.common.exception.CourseException.InvalidCourseDataException;
import com.osc.oscms.common.exception.CourseException.TeacherNotFoundException;
import com.osc.oscms.common.exception.CourseException.EnrollmentNotFoundException;

import com.osc.oscms.common.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 统一捕获并处理异常，返回标准化格式
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 @Valid 参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleValidation(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("'%s': %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining("; "));
        logger.warn("Validation Error: {}", errorMessage);
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), "参数校验失败: " + errorMessage);
    }

    // ===== 注册相关异常 =====
    @ExceptionHandler(UserIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleUserIdAlreadyExists(UserIdAlreadyExistsException ex) {
        logger.warn("Registration failed: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        logger.warn("Registration failed: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        logger.warn("Registration failed: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(RegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleRegistrationException(RegistrationException ex) {
        logger.error("Registration error: {}", ex.getMessage(), ex);
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), "注册失败: " + ex.getMessage());
    }

    // ===== 课程相关异常 =====
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleCourseNotFoundException(CourseNotFoundException ex) {
        logger.error("Course error: {}", ex.getMessage(), ex);
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleTeacherNotFoundException(TeacherNotFoundException ex) {
        logger.error("Course error: {}", ex.getMessage(), ex);
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidCourseDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleInvalidCourseDataException(InvalidCourseDataException ex) {
        logger.error("Course error: {}", ex.getMessage(), ex);
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(CourseCodeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleCourseAlreadyCompletedException(CourseCodeAlreadyExistsException ex) {
        logger.error("Course error: {}", ex.getMessage(), ex);
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // ===== 班级相关异常 =====
    @ExceptionHandler(ClazzNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleClassNotFoundException(ClazzNotFoundException ex) {
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(DuplicateStudentInClassException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<Object> handleDuplicateStudentInClassException(DuplicateStudentInClassException ex) {
        return ApiResponse.fail(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(DuplicateTAInClassException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<Object> handleDuplicateTAInClassException(DuplicateTAInClassException ex) {
        return ApiResponse.fail(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(TANotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleTANotFoundException(TANotFoundException ex) {
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleStudentNotFoundException(StudentNotFoundException ex) {
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidClassDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleInvalidClassDataException(InvalidClassDataException ex) {
        logger.error("Course error: {}", ex.getMessage(), ex);
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // ===== 用户相关异常 =====
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleUserNotFoundException(UserNotFoundException ex) {
        logger.warn("User not found: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidRoleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleInvalidRoleException(InvalidRoleException ex) {
        logger.warn("Invalid role: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // ===== 题目异常 =====
    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleQuestionNotFoundException(QuestionNotFoundException ex) {
        logger.warn("Question not found: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // ===== 资料异常 =====
    @ExceptionHandler(MaterialNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleMaterialNotFoundException(MaterialNotFoundException ex) {
        logger.warn("Material not found: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // ===== 存储异常 =====
    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleFileStorageException(FileStorageException ex) {
        logger.warn("File storage error: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleEnrollmentNotFoundException(EnrollmentNotFoundException ex) {
        logger.warn("Enrollment/Association not found: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // ===== 作业相关异常 =====
    @ExceptionHandler(AssignmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleAssignmentNotFound(AssignmentNotFoundException ex) {
        logger.warn("Assignment not found: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(SubmissionDeadlineExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleSubmissionDeadlineExceeded(SubmissionDeadlineExceededException ex) {
        logger.warn("Submission rejected due to deadline: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(ResubmissionNotAllowedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<Object> handleResubmissionNotAllowed(ResubmissionNotAllowedException ex) {
        logger.warn("Submission rejected, resubmission not allowed: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(StudentNotEnrolledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<Object> handleStudentNotEnrolled(StudentNotEnrolledException ex) {
        logger.warn("Submission rejected, student not enrolled or invalid context: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<Object> handleAccessDenied(AccessDeniedException ex) {
        logger.warn("Access denied: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.FORBIDDEN.value(), "权限不足：" + ex.getMessage());
    }

    @ExceptionHandler(SubmissionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleSubmissionNotFound(SubmissionNotFoundException ex) {
        logger.warn("Submission not found: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(GradingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleGradingException(GradingException ex) {
        logger.warn("Grading error: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidEnrollmentActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleInvalidEnrollmentAction(InvalidEnrollmentActionException ex) {
        logger.warn("Invalid enrollment action: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(FavoriteAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleFavoriteAlreadyExistException(FavoriteAlreadyExistException ex) {
        logger.warn("Favorite question already exists: {}", ex.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // ===== 其他异常 =====
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleGenericException(Exception ex) {
        logger.error("Unexpected system error occurred:", ex);
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误，请稍后再试或联系管理员");
    }
}




