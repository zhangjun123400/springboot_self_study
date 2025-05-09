package com.zhangjun.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhangjun.common.api.CommonResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * 认证用户无权限访问的处理器
 * @Author zhangjun
 * @Date 2025/4/29 23:53
 * @Version 1.0
 */
@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //发生这个行为，做相应的处理，给一个响应的结果

        //设置客户端响应的内容类型
        response.setContentType("application/json;charset=utf-8");

        //通过输出流
        ServletOutputStream outputStream = response.getOutputStream();

        //调用这个fastjson 进行CommonResult对象的序列化
        String jsonString = JSON.toJSONString(CommonResult.forbidden(accessDeniedException.getMessage()), SerializerFeature.DisableCircularReferenceDetect);

        outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }
}
