package com.osc.oscms.common.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 题目DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    /**
     * 题目ID
     */
    private Long id;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目类型
     */
    private String type;

    /**
     * 难度等级
     */
    private String difficulty;

    /**
     * 选择题选项列表（仅选择题有效）
     */
    private List<String> choices;

    /**
     * 正确答案或参考答案
     */
    private String correctAnswer;

    /**
     * 题目分数
     */
    private Integer score;
}
