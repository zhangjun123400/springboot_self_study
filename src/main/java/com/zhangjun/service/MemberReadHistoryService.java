package com.zhangjun.service;

import com.zhangjun.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * 会员浏览记录管理Service
 * @Author zhangjun
 * @Date 2025/5/8 22:14
 * @Version 1.0
 */
public interface MemberReadHistoryService {

    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<MemberReadHistory> list(Long memberId);
}
