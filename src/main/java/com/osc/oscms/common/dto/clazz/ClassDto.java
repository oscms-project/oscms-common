package com.osc.oscms.common.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    /**
     * 班级ID
     */
    private Long id;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级代码
     */
    private String code;

    /**
     * 所属课程ID
     */
    private Long courseId;
}
