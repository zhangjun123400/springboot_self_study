package com.zhangjun.controller;

import com.zhangjun.common.api.CommonResult;
import com.zhangjun.exception.CustomerAuthenticationException;
import com.zhangjun.mbg.model.user.UmsAdmin;
import com.zhangjun.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author zhangjun
 * @Date 2025/4/28 17:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Operation(summary = "用户登陆")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult<? extends Map> login(@RequestBody UmsAdmin user) {

        Map<String,Object> map =  userService.login(user);

        if (!map.get("token").equals("")&&!map.get("username").equals(""))
        {
            return CommonResult.success(map,"登陆成功");
        }

        return CommonResult.failed("登陆失败");
    }


    @Operation(summary = "用户登出")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response)
    {

        //在logout中要获取jwt
        String token = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        if(ObjectUtils.isEmpty(token)){
            throw new CustomerAuthenticationException("token为空");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            //1、清除上下文
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            //2、清除Redis
            redisTemplate.delete("token_"+token);

        }

        return CommonResult.success("用户退出成功");
    }
}
