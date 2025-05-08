package com.zhangjun.mbg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
//@ApiModel(value = "PmsBrand",description = "商品品牌")
@Schema(description = "商品品牌")
public class PmsBrand implements Serializable {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "名称")
    private String name;

    /**
     * 首字母
     */
    @Schema(description = "首字母")
    private String firstLetter;

    @Schema(description = "排序")
    private Integer sort;

    /**
     * 是否为品牌制造商：0->不是；1->是
     */
    @Schema(description = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;

    @Schema(description = "是否显示")
    private Integer showStatus;

    /**
     * 产品数量
     */
    @Schema(description = "产品数量")
    private Integer productCount;

    /**
     * 产品评论数量
     */
    @Schema(description = "产品评论数量")
    private Integer productCommentCount;

    /**
     * 品牌logo
     */
    @Schema(description = "品牌logo")
    private String logo;

    /**
     * 专区大图
     */
    @Schema(description = "专区大图")
    private String bigPic;

    /**
     * 品牌故事
     */
    @Schema(description = "品牌故事")
    private String brandStory;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", firstLetter=").append(firstLetter);
        sb.append(", sort=").append(sort);
        sb.append(", factoryStatus=").append(factoryStatus);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", productCount=").append(productCount);
        sb.append(", productCommentCount=").append(productCommentCount);
        sb.append(", logo=").append(logo);
        sb.append(", bigPic=").append(bigPic);
        sb.append(", brandStory=").append(brandStory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}