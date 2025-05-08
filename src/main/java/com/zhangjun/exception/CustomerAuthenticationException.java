package com.zhangjun.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author zhangjun
 * @Date 2025/4/29 23:49
 * @Version 1.0
 */
public class CustomerAuthenticationException extends AuthenticationException {


    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}
