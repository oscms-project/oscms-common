package com.osc.oscms.common.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 课程创建/更新DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateDto {

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空")
    private String name;

    /**
     * 课程代码
     */
    @NotBlank(message = "课程代码不能为空")
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
     * 课程章节列表（可选）
     */
    private List<ChapterCreateDto> chapters;

    /**
     * 课程是否已结课
     */
    private Boolean completed;
}
