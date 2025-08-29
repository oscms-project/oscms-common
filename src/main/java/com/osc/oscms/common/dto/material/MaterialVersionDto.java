package com.osc.oscms.common.dto.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * 资料版本信息DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialVersionDto {

    /**
     * 资料ID
     */
    private Long materialId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件访问URL
     */
    private String url;

    /**
     * 版本描述
     */
    private String description;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime uploadedAt;
}