package com.osc.oscms.common.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 手动批改请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManualGradeDto {

    /**
     * 批改成绩列表
     */
    @NotEmpty(message = "批改成绩列表不能为空")
    @Size(min = 1, message = "至少需要批改一个题目")
    private List<GradeItemDto> grades;
}
