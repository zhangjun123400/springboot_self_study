package com.zhangjun.controller;

import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mbg.model.PmsBrand;
import com.zhangjun.service.PmsBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.PushBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/22 20:12
 * @Version 1.0
 */
@Controller
//@Api(tags = "PmsBrandController")
@Tag(name = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger logger = LoggerFactory.getLogger(PmsBrandController.class);

    @Operation(summary="获取所有品牌")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getAllBrand() {
        CommonResult<List<PmsBrand>> commonResult = CommonResult.success(pmsBrandService.listAllBrand());

        logger.info(commonResult.getData().toString());
        logger.info(commonResult.getMessage());
        logger.info(Long.toString(commonResult.getCode()));

        return commonResult;
    }

    @Operation(summary="添加品牌")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count ==1) {
            commonResult = CommonResult.success(pmsBrand);
            logger.info("createBrand success:{}", pmsBrand);
        }
        else {
            commonResult = CommonResult.failed("操作失败");
            logger.info("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @Operation(summary="更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PmsBrand> updateBrand(@PathVariable("id")Long id, @RequestBody PmsBrand pmsBrand, BindingResult bindingResult) {

        CommonResult<PmsBrand>  commonResult;

        int count = pmsBrandService.updateBrand(id,pmsBrand);

        if (count ==1) {
            commonResult = CommonResult.success(pmsBrand);
            logger.info("updateBrand success:{}", pmsBrand);
        }
        else {
            commonResult = CommonResult.failed("操作失败");
            logger.info("updateBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @Operation(summary="删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id")Long id, PushBuilder pushBuilder){
        CommonResult commonResult;
        int count = pmsBrandService.deleteBrand(id);
        if (count ==1) {
            logger.info("deleteBrand success:id={}", id);
            return CommonResult.success(null);
        }
        else{
            logger.info("deleteBrand failed:id={}", id);
            return CommonResult.failed("操作失败");
        }

    }

    @Operation(summary="分页查询品牌列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1")
                                                            @Parameter(description = "页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize",defaultValue = "3")
                                                        @Parameter(description = "每页数量") Integer pageSize) {
        List<PmsBrand> brandList =pmsBrandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));

    }

    @Operation(summary="获取指定id的品牌详情")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id")Long id)
    {
        return CommonResult.success(pmsBrandService.getBrandById(id));
    }



}
