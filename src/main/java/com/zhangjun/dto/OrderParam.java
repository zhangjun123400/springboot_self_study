package com.zhangjun.dto;

import lombok.Data;

/**
 * @Author zhangjun
 * @Date 2025/5/9 23:14
 * @Version 1.0
 */
@Data
public class OrderParam {

    //收货地址id
    private Long memberReceiveAddressId;

    //优惠券id
    private Long couponId;

    //使用的积分数
    private Integer userIntegration;

    //支付方式
    private Integer payType;
}
