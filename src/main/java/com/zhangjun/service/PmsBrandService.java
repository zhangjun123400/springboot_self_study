package com.zhangjun.service;

import com.zhangjun.mbg.model.PmsBrand;
import com.zhangjun.mbg.model.PmsBrandExample;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/22 20:00
 * @Version 1.0
 */
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand pmsBrand);

    int updateBrand(Long id,PmsBrand pmsBrand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrandById(Long id);
}
