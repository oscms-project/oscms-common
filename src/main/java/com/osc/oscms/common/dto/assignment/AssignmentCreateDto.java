package com.osc.oscms.common.dto.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 作业创建请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentCreateDto {

    /**
     * 作业标题
     */
    @NotBlank(message = "作业标题不能为空")
    @Size(max = 255, message = "作业标题长度不能超过255个字符")
    private String title;

    /**
     * 作业描述
     */
    private String description;

    /**
     * 所属课程ID
     */
    @NotNull(message = "课程ID不能为空")
    private Long courseId;

    /**
     * 作业开放时间
     */
    @NotNull(message = "作业开放时间不能为空")
    private LocalDateTime openTime;

    /**
     * 作业截止时间
     */
    @NotNull(message = "作业截止时间不能为空")
    private LocalDateTime dueDate;

    /**
     * 是否允许重复提交
     */
    private Boolean allowResubmit = false;

    /**
     * 关联的题目ID列表（可选）
     */
    private List<Long> questionIds;
}