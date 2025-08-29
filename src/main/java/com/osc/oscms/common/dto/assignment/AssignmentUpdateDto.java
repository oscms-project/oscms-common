package com.osc.oscms.common.dto.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 作业更新请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentUpdateDto {

    /**
     * 作业标题
     */
    @Size(max = 255, message = "作业标题长度不能超过255个字符")
    private String title;

    /**
     * 作业描述
     */
    private String description;

    /**
     * 所属课程ID
     */
    private Long courseId;

    /**
     * 所属班级ID
     */
    private Long classId;

    /**
     * 作业开放时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime openTime;

    /**
     * 作业截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueDate;

    /**
     * 是否允许重复提交
     */
    private Boolean allowResubmit;

    /**
     * 题目ID列表（全量替换逻辑）
     */
    private List<Long> questionIds;
}