package com.zhangjun.controller;

import com.github.pagehelper.Page;
import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.nosql.elasticsearch.document.EsProduct;
import com.zhangjun.service.EsProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/6 22:46
 * @Version 1.0
 */
@Controller
@Tag(name = "EsProductController",description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;


    @Operation(summary = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult<Integer> importAllList()
    {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @Operation(summary = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult<Object> delete(@PathVariable Long id)
    {
       int count = esProductService.delete(id);
       if(count==1)
       {
           return CommonResult.success(null);
       }
       else
       {
           return CommonResult.success("ES库本不存在");
       }

    }

    @Operation(summary = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }


    @Operation(summary = "根据id创建商品")
    @RequestMapping(value = "/create/{id}",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct esProduct = esProductService.create(id);
        if(esProduct != null){
            return CommonResult.success(esProduct);
        }else
        {
            return CommonResult.failed();
        }

    }


    @Operation(summary = "简单搜索")
    @RequestMapping(value = "/search/simple" ,method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        Page<EsProduct> page = esProductService.search(keyword,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }


}
