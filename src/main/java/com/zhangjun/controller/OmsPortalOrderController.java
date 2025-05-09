package com.zhangjun.controller;

import com.zhangjun.dto.OrderParam;
import com.zhangjun.service.OmsPortalOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zhangjun
 * @Date 2025/5/9 23:52
 * @Version 1.0
 */
@Controller
@Tag(name = "OmsPortalOrderController",description = "订单管理")
@RequestMapping("/order")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @Operation(summary = "根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public Object generateOrder(@RequestBody OrderParam orderParam){
        return omsPortalOrderService.generateOrder(orderParam);
    }
}
