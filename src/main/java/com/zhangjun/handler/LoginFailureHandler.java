package com.zhangjun.handler;

import com.alibaba.fastjson2.JSON;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.common.api.IErrorCode;
import com.zhangjun.common.api.ResultCode;
import com.zhangjun.exception.CustomerAuthenticationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户认证校验失败的处理器
 * @Author zhangjun
 * @Date 2025/4/30 02:01
 * @Version 1.0
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String message = "登陆失败";
        String result;

        //异常类型与错误信息映射
        Map<Class<? extends Exception>,String> errorMap = new HashMap<>();
        errorMap.put(AccountExpiredException.class, "账户过期,登录失败！");
        errorMap.put(BadCredentialsException.class, "用户名或密码错误,登录失败！");
        errorMap.put(CredentialsExpiredException.class, "密码过期,登录失败！");
        errorMap.put(DisabledException.class, "账户被禁用,登录失败！");
        errorMap.put(LockedException.class, "账户被锁,登录失败！");
        errorMap.put(InternalAuthenticationServiceException.class, "账户不存在,登录失败！");

        if (exception instanceof CustomerAuthenticationException)
        {
            message = exception.getMessage()!=null ? exception.getMessage():"认证失败";
            //将错误信息转换成JSON
            result = JSON.toJSONString(CommonResult.applicationError(message));
        }
        else
        {
            message = errorMap.getOrDefault(exception.getClass(),exception.getClass().getSimpleName());

            //将错误信息转换成JSON
            result = JSON.toJSONString(CommonResult.failed(message));
        }


        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }


}
