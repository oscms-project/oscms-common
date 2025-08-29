package com.osc.oscms.common.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * ID列表请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdListDto {

    /**
     * ID列表
     */
    @NotEmpty(message = "ID列表不能为空")
    @Size(min = 1, message = "至少需要提供一个ID")
    private List<Long> ids;
}
