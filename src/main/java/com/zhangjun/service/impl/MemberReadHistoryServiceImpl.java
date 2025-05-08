package com.zhangjun.service.impl;

import com.zhangjun.nosql.mongodb.document.MemberReadHistory;
import com.zhangjun.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.zhangjun.service.MemberReadHistoryService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/8 22:16
 * @Version 1.0
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        mongoTemplate.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {

        List<MemberReadHistory> list = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            list.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(list);

        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
