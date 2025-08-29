package com.osc.oscms.common.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 作业提交DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDto {

    /**
     * 提交ID
     */
    private Long id;

    /**
     * 作业ID
     */
    private Long assignmentId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 答案结果列表
     */
    private List<AnswerResultDto> answers;

    /**
     * 自动评分（客观题）
     */
    private Integer autoScore;

    /**
     * 手动评分（主观题）
     */
    private Integer manualScore;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 提交状态
     */
    private String status;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime submittedAt;

    /**
     * 最后更新时间（如批改后）
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
