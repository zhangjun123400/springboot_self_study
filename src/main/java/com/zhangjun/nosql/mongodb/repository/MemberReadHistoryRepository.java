package com.zhangjun.nosql.mongodb.repository;

import com.zhangjun.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/8 21:53
 * @Version 1.0
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {

    /**
     * 会员商品浏览历史Repository
     * @param memberId
     * @return
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);

    List<MemberReadHistory> findByProductIdOrderByCreateTimeDesc(Long projectId);
}
