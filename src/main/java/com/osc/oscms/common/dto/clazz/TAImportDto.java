package com.osc.oscms.common.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 批量添加助教请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TAImportDto {

    /**
     * 要添加的助教ID列表
     */
    @NotEmpty(message = "助教ID列表不能为空")
    @Size(min = 1, message = "至少需要添加一个助教")
    private List<String> taIds;
}