package com.osc.oscms.common.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

/**
 * 分页请求DTO
 */
@Data
public class PageRequest {
    @Min(value = 1, message = "页码不能小于1")
    private int page = 1;

    @Min(value = 1, message = "每页大小不能小于1")
    @Max(value = 100, message = "每页大小不能超过100")
    private int size = 10;

    private String sortBy = "id";
    private String sortDir = "asc";
}




