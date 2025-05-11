package com.zhangjun.component;

import com.zhangjun.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zhangjun
 * @Date 2025/5/9 23:34
 * @Version 1.0
 */
@Component
public class CancelOrderSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Long orderId,final long delayTime){
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, message -> {
            message.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return message;
        });

        LOGGER.info("send delay message orderId:{}",orderId);
    }

}
