package com.zhangjun.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhangjun
 * @Date 2025/5/10 17:41
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
public class MinioUploadDto {

    @Schema(description ="文件访问URL")
    private String url;

    @Schema(description ="文件名称")
    private String name;
}
