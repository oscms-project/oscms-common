package com.osc.oscms.common.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 导入结果DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportResultDto {

    /**
     * 成功导入/添加的数量
     */
    private Integer importedCount;
}
