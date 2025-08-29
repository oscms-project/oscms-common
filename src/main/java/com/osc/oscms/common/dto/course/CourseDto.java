package com.osc.oscms.common.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 课程DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    /**
     * 课程ID
     */
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程代码
     */
    private String code;

    /**
     * 课程大纲
     */
    private String outline;

    /**
     * 课程目标
     */
    private String objectives;

    /**
     * 考核方式
     */
    private String assessment;

    /**
     * 教师ID
     */
    private String teacherId;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 课程章节列表
     */
    private List<ChapterCreateDto> chapters;

    /**
     * 课程是否已完成
     */
    private Boolean completed;
}
