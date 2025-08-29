package com.osc.oscms.common.dto.course;

import lombok.Data;

/**
 * 章节创建DTO
 */
@Data
public class ChapterCreateDto {
    private String title;
    private Integer order;
}