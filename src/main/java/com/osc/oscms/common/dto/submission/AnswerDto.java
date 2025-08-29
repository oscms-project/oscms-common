package com.osc.oscms.common.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

/**
 * 学生答案DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

    /**
     * 对应的题目ID
     */
    @NotNull(message = "题目ID不能为空")
    private Long questionId;

    /**
     * 学生的回答内容
     */
    private String response;
}
