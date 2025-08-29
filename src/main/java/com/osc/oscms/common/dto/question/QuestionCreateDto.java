package com.osc.oscms.common.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 题目创建DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDto {

    /**
     * 题目标题
     */
    @NotBlank(message = "题目标题不能为空")
    private String title;

    /**
     * 题目类型
     */
    @NotBlank(message = "题目类型不能为空")
    @Pattern(regexp = "^(choice|coding|short_answer)$", message = "题目类型必须是choice、coding或short_answer")
    private String type;

    /**
     * 选择题选项列表（选择题必填，编程题可为空）
     */
    private List<String> choices;

    /**
     * 正确答案或参考答案
     */
    @NotBlank(message = "正确答案不能为空")
    private String correctAnswer;

    /**
     * 题目分数
     */
    @NotNull(message = "题目分数不能为空")
    @Min(value = 0, message = "题目分数不能小于0")
    private Integer score;
}
