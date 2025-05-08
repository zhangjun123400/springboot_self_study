package com.zhangjun.service.impl;

import com.github.pagehelper.Page;
import com.zhangjun.dao.EsProductDao;
import com.zhangjun.nosql.elasticsearch.document.EsProduct;
import com.zhangjun.nosql.elasticsearch.repository.EsProductRepository;
import com.zhangjun.service.EsProductService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/6 21:46
 * @Version 1.0
 */
@Service
public class EsProductServiceImpl implements EsProductService {

    private static final Logger logger = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductDao esProductDao;

    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(null);

        Iterable<EsProduct> iterable = esProductRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = iterable.iterator();
        int count = 0;

        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        if (esProductRepository.existsById(id)) {
            esProductRepository.deleteById(id);
            return 1;
        }else
        {
            return 0;
        }

    }

    @Override
    public EsProduct create(Long id) {
        EsProduct esProduct = null;
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(id);
        for (EsProduct esProduct1 : esProductList) {
            esProduct = esProductRepository.save(esProduct1);
        }
        return esProduct;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
               EsProduct esProduct = new EsProduct();
               esProduct.setId(id);
               esProductList.add(esProduct);
            }
            esProductRepository.deleteAll(esProductList);
        }

    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword,keyword,keyword,pageable);
    }
}
