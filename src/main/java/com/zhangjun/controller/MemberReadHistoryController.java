package com.zhangjun.controller;

import com.zhangjun.common.api.CommonResult;
import com.zhangjun.nosql.mongodb.document.MemberReadHistory;
import com.zhangjun.service.MemberReadHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理
 * @Author zhangjun
 * @Date 2025/5/8 23:25
 * @Version 1.0
 */
@Controller
@Tag(name = "MemberReadHistoryController",description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    MemberReadHistoryService memberReadHistoryService;


    @Operation(summary = "创建浏览记录")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @Operation(summary = "删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult delete(@RequestParam("ids") List<String> ids){
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "展示浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('/product/**')")
    public CommonResult<List<MemberReadHistory>> list(Long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return CommonResult.success(memberReadHistoryList);
    }
}
