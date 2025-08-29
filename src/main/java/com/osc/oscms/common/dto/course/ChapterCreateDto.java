package com.osc.oscms.common.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

/**
 * 章节创建DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterCreateDto {

    /**
     * 章节标题
     */
    @NotBlank(message = "章节标题不能为空")
    private String title;

    /**
     * 章节顺序（从1开始）
     */
    @Min(value = 0, message = "章节顺序不能小于0")
    private Integer order;
}