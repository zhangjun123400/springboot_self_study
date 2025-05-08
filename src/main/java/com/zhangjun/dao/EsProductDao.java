package com.zhangjun.dao;

import com.zhangjun.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/6 22:04
 * @Version 1.0
 */
public interface EsProductDao {

    @Select("<script>\n" +
            "        select\n" +
            "            p.id id,\n" +
            "            p.product_sn productSn,\n" +
            "            p.brand_id brandId,\n" +
            "            p.brand_name brandName,\n" +
            "            p.product_category_id productCategoryId,\n" +
            "            p.product_category_name productCategoryName,\n" +
            "            p.pic pic,\n" +
            "            p.name name,\n" +
            "            p.sub_title subTitle,\n" +
            "            p.price price,\n" +
            "            p.sale sale,\n" +
            "            p.new_status newStatus,\n" +
            "            p.recommand_status recommandStatus,\n" +
            "            p.stock stock,\n" +
            "            p.promotion_type promotionType,\n" +
            "            p.keywords keywords,\n" +
            "            p.sort sort,\n" +
            "            pav.id attr_id,\n" +
            "            pav.value attr_value,\n" +
            "            pav.product_attribute_id attr_product_attribute_id,\n" +
            "            pa.type attr_type,\n" +
            "            pa.name attr_name\n" +
            "        from pms_product p\n" +
            "        left join pms_product_attribute_value pav on p.id = pav.product_id\n" +
            "        left join pms_product_attribute pa on pav.product_attribute_id= pa.id\n" +
            "        where delete_status = 0 and publish_status = 1\n" +
            "        <if test=\"id!=null\">\n" +
            "            and p.id=#{id}\n" +
            "        </if>\n" +
            "\t</script>")
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
