package com.osc.oscms.common.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

/**
 * 单题批改DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeItemDto {

    /**
     * 要批改的题目ID
     */
    @NotNull(message = "题目ID不能为空")
    private Long questionId;

    /**
     * 给定的分数
     */
    @NotNull(message = "分数不能为空")
    @Min(value = 0, message = "分数不能小于0")
    private Integer score;

    /**
     * 可选的教师评语
     */
    private String feedback;
}
