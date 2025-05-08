package com.zhangjun.nosql.elasticsearch.repository;

import com.github.pagehelper.Page;
import com.zhangjun.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author zhangjun
 * @Date 2025/5/6 21:04
 * @Version 1.0
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {


    /**
     * 搜索查询
     * @param name
     * @param subTitle
     * @param keywords
     * @param pageable
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable pageable);


}