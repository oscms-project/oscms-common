package com.osc.oscms.common.dto.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 资料信息DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto {

    /**
     * 资料ID
     */
    private Long id;

    /**
     * 所属课程ID
     */
    private Long courseId;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 资料类型
     */
    private String type;

    /**
     * 章节顺序
     */
    private Integer chapterOrder;

    /**
     * 文件访问URL
     */
    private String url;

    /**
     * 可见班级ID列表
     */
    private List<String> visibleForClasses;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    /**
     * 最新版本号
     */
    private Integer latestVersion;
}
