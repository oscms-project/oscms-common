package com.osc.oscms.common.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 批量导入学生请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentImportDto {

    /**
     * 要导入的学生ID列表
     */
    @NotEmpty(message = "学生ID列表不能为空")
    @Size(min = 1, message = "至少需要导入一个学生")
    private List<String> studentIds;
}