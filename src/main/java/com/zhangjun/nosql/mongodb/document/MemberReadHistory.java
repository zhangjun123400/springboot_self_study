package com.zhangjun.nosql.mongodb.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author zhangjun
 * @Date 2025/5/8 22:01
 * @Version 1.0
*/
@Data
@EqualsAndHashCode
@Document
public class MemberReadHistory {
    @Id
    private String id;

    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;
}
