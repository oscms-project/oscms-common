package com.osc.oscms.common.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 答案结果详情DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResultDto {

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 学生答案
     */
    private String response;

    /**
     * 该题得分
     */
    private Integer score;

    /**
     * 是否正确（主要用于客观题）
     */
    private Boolean correct;

    /**
     * 教师反馈
     */
    private String feedback;

    /**
     * 正确答案
     */
    private String correctAnswer;

    /**
     * 题目类型
     */
    private String questionType;
}
