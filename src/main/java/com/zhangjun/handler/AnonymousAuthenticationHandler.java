package com.zhangjun.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhangjun.common.api.CommonResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 客户端发送认证数据的提交时出现异常，或者匿名用户访问受限资源的处理器
 * @Author zhangjun
 * @Date 2025/4/30 00:25
 * @Version 1.0
 */
@Component
public class AnonymousAuthenticationHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        //设置客户端响应的内容类型
        response.setContentType("application/json;charset=utf-8");

        //通过输出流
        ServletOutputStream outputStream = response.getOutputStream();

        String jsonString;

        if (authException instanceof BadCredentialsException) {
            //用户名或
            //调用这个fastjson 进行CommonResult对象的序列化
            jsonString = JSON.toJSONString(CommonResult.invalidUsernameorPassword("用户名或者密码错误"), SerializerFeature.DisableCircularReferenceDetect);

        }else if (authException instanceof InternalAuthenticationServiceException)
        {
            //调用这个fastjson 进行CommonResult对象的序列化
            jsonString = JSON.toJSONString(CommonResult.emptyUsernameorPassword("用户名为空"), SerializerFeature.DisableCircularReferenceDetect);

        }else {
            //调用这个fastjson 进行CommonResult对象的序列化
            jsonString = JSON.toJSONString(CommonResult.failed("匿名用户无权限访问"), SerializerFeature.DisableCircularReferenceDetect);

        }



        outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
