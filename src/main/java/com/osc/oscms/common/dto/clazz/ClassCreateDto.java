package com.osc.oscms.common.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

/**
 * 班级创建DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassCreateDto {

    /**
     * 班级名称
     */
    @NotBlank(message = "班级名称不能为空")
    private String name;

    /**
     * 班级代码
     */
    @NotBlank(message = "班级代码不能为空")
    private String code;

    /**
     * 所属课程ID
     */
    @NotNull(message = "所属课程ID不能为空")
    private Long courseId;
}
