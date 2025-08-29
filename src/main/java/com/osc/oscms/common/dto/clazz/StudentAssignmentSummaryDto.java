package com.osc.oscms.common.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生作业完成情况摘要DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAssignmentSummaryDto {

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 总作业数
     */
    private Integer totalAssignments;

    /**
     * 已完成作业数
     */
    private Integer completedAssignments;

    /**
     * 未完成作业数
     */
    private Integer pendingAssignments;
}
