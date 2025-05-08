package com.zhangjun.nosql.elasticsearch.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Author zhangjun
 * @Date 2025/5/6 21:40
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 11;

    private Long id; // 主键（未使用 @Id 注解，需结合框架确认主键标识方式）

    private Long productAttributeId; // 关联属性ID（无注解，默认映射为 Elasticsearch 的 `long` 类型）

    @Field(type = FieldType.Keyword)
    private String value; // 属性值（存储为不分词的 Keyword 类型）

    private Integer type; // 属性参数类型（0=规格，1=参数；建议使用枚举替代 Integer）

    @Field(type = FieldType.Keyword)
    private String name; // 属性名称（存储为 Keyword 类型）
}
