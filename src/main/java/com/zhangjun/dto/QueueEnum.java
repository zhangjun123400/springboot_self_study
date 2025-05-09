package com.zhangjun.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author zhangjun
 * @Date 2025/5/9 23:16
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),

    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall.order.cancel.ttl", "mall.order.cancel.ttl");

    /**
     * 交换机名称
     */
    private final String exchange;

    /**
     * 队列名称
     */
    private final String name;

    /**
     * 路由键
     */
    private final String routeKey;


}
