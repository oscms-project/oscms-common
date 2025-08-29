package com.osc.oscms.common.dto.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * 资料上传请求DTO
 * 
 * @author OSCMS Development Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUploadDto {

    /**
     * 要上传的文件
     */
    @NotNull(message = "文件不能为空")
    private MultipartFile file;

    /**
     * 章节顺序
     */
    @NotNull(message = "章节顺序不能为空")
    @Min(value = 0, message = "章节顺序不能小于0")
    private Integer chapterOrder;

    /**
     * 资料类型
     */
    @NotBlank(message = "资料类型不能为空")
    private String type;

    /**
     * 资料描述
     */
    private String description;

    /**
     * 可见班级ID列表
     */
    private List<String> visibleForClasses;
}