package com.osc.oscms.common.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 作业提交请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionCreateDto {

    /**
     * 学生ID
     */
    @NotBlank(message = "学生ID不能为空")
    private String studentId;

    /**
     * 答案列表
     */
    @NotEmpty(message = "答案列表不能为空")
    @Size(min = 1, message = "至少需要提供一个答案")
    private List<AnswerDto> answers;
}
