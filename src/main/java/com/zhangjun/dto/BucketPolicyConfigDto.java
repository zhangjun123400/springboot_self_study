package com.zhangjun.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/10 17:33
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@Builder
public class BucketPolicyConfigDto {

    private String Version;

    private List<Statement> Statement;

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Statement{
        private String Effect;
        private String Principal;
        private String Action;
        private String Resource;
    }
}
