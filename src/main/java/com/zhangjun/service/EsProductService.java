package com.zhangjun.service;

import com.github.pagehelper.Page;
import com.zhangjun.nosql.elasticsearch.document.EsProduct;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/6 21:43
 * @Version 1.0
 */
public interface EsProductService {

    /**
     * 从数据库中导入所有的商品到ES
     * @return
     */
    int importAll();

    /**
     * 根据ID删除商品
     * @param id
     */
    int delete(Long id);

    /**
     * 根据ID创建商品
     * @param id
     * @return
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
