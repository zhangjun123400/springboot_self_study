package com.zhangjun.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjun.common.utils.JwtUtil;
import com.zhangjun.domain.vo.LoginUser;
import com.zhangjun.mbg.mapper.user.UmsAdminMapper;
import com.zhangjun.mbg.model.user.UmsAdmin;
import com.zhangjun.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangjun
 * @Date 2025/4/28 17:26
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<String,Object> login(UmsAdmin user) {

        //不需要连接数据库
        //把登陆时候的用户名和密码封装成一个UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

        //通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authentication)){
           throw new RuntimeException("登陆失败");
        }

        //如果认证成功，就从authentication对象的getPrincipal方法中拿到认证通过后的登陆用户对象
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //生成jwt,使用fastjson的方法，把对象转程字符串
        String loginUserString = JSON.toJSONString(loginUser);

        //生成令牌
        String jwt = JwtUtil.createJWT(loginUserString,null);

        //jwt的键名
        String toeknKey = "token_"+jwt;

        //存储redis白名单
        redisTemplate.opsForValue().set(toeknKey,jwt,JwtUtil.JWT_TTL/1000, TimeUnit.SECONDS);


        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("username",loginUser.getUsername());

        return map;
    }




}
