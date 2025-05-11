package com.zhangjun.component;

import com.zhangjun.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhangjun
 * @Date 2025/5/9 23:43
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @RabbitHandler
    @Transactional
    public void handle(Long orderId){
        LOGGER.info("Queue 1 receive delay message orderId:{}",orderId);
        omsPortalOrderService.cancelOrder(orderId);
    }



}
