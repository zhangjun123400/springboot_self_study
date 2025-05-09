package com.zhangjun.service;

import com.zhangjun.common.api.CommonResult;
import com.zhangjun.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhangjun
 * @Date 2025/5/9 23:45
 * @Version 1.0
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);


    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
