package com.aiyangniu.mall.enter.model.bo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Minio Bucket访问策略配置
 *
 * @author lzq
 * @date 2023/05/30
 */
@Data
@EqualsAndHashCode
@Builder
public class BucketPolicyConfig {

    private String version;
    private List<Statement> statement;

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Statement {
        private String effect;
        private String principal;
        private String action;
        private String resource;
    }
}
