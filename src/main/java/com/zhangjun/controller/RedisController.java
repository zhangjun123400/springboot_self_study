package com.zhangjun.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mbg.model.PmsBrand;
import com.zhangjun.service.PmsBrandService;
import com.zhangjun.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author zhangjun
 * @Date 2025/4/24 16:53
 * @Version 1.0
 */
@Controller
@Tag(name = "RedisController",description = "redis测试")
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private PmsBrandService brandService;

    @Operation(summary = "测试简单缓存")
    @RequestMapping(value = "/simpleTest",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> simpleTest()
    {
        List<PmsBrand> brandList = brandService.listBrand(1,10);
        PmsBrand pmsBrand = brandList.get(1);
        String key ="redis:brand:"+pmsBrand.getId();
        redisService.set(key,pmsBrand);
        PmsBrand cacheBrand = (PmsBrand) redisService.get(key);
        return CommonResult.success(cacheBrand);
    }

    @Operation(summary = "测试Hash结构的缓存")
    @RequestMapping(value = "/hashTest",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> hasHTest(){
        List<PmsBrand> brandList = brandService.listBrand(1,10);
        PmsBrand pmsBrand = brandList.get(1);
        String key ="redis:hash:"+pmsBrand.getId();
        Map<String,Object> value = BeanUtil.beanToMap(pmsBrand);
        redisService.hSetAll(key,value);
        Map<Object,Object> cacheValue = redisService.hGetAll(key);
        PmsBrand cacheBrand = BeanUtil.toBean(cacheValue,PmsBrand.class);
        return CommonResult.success(cacheBrand);
    }

    @Operation(summary = "测试Set结构的缓存")
    @RequestMapping(value = "/setTest",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Set<Object>> setTest()
    {
        List<PmsBrand> brandList = brandService.listBrand(1,10);
        String key ="redis:set:all";
        redisService.sAdd(key, (Object[]) ArrayUtil.toArray(brandList, PmsBrand.class));
        redisService.sRemove(key,brandList.get(0));
        Set<Object> cacheBrandList = redisService.sMembers(key);

        return CommonResult.success(cacheBrandList);

    }

    @Operation(summary = "测试List结构的缓存")
    @RequestMapping(value = "/listTest",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Object>> listTest(){
        List<PmsBrand> brandList = brandService.listBrand(1,10);

        String key ="redis:list:all";
        redisService.lPush(key, (Object[]) ArrayUtil.toArray(brandList, PmsBrand.class));
        redisService.lRemove(key,1,brandList.get(0));
        List<Object> cacheBrandList = redisService.lRange(key,0,3);
        return CommonResult.success(cacheBrandList);
    }




}
