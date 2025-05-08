package com.zhangjun.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhangjun.config.RedisConfig;
import com.zhangjun.mbg.mapper.PmsBrandMapper;
import com.zhangjun.mbg.model.PmsBrand;
import com.zhangjun.mbg.model.PmsBrandExample;
import com.zhangjun.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/22 20:04
 * @Version 1.0
 */
@Service
public class PmsBrandServiceImpl  implements PmsBrandService {

    @Autowired
    PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return pmsBrandMapper.selectByExample(new PmsBrandExample());

    }

    @CachePut(value = RedisConfig.REDIS_KEY_DATABASE,key = "'pms:brand:'+#id")
    @Override
    public int createBrand(PmsBrand pmsBrand) {
        return pmsBrandMapper.insert(pmsBrand);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE,key = "'pms:brand:'+#id")
    @Override
    public int updateBrand(Long id, PmsBrand pmsBrand) {
        pmsBrand.setId(id);
        return pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE,key = "'pms:brand:'+#id")
    @Override
    public int deleteBrand(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE,key="'pms:brand:'+#id",unless = "#result==null")
    @Override
    public PmsBrand getBrandById(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }
}

















