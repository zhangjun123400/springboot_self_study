package com.zhangjun.component;

import com.zhangjun.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zhangjun
 * @Date 2025/5/10 00:22
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "mall.order.cancel2")
public class CancelOrderReceiver2 {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("Queue 2 receive delay message orderId:{}",orderId);
        omsPortalOrderService.cancelOrder(orderId);
    }

}
