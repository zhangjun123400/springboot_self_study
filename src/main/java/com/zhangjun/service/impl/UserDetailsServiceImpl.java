package com.zhangjun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangjun.domain.vo.LoginUser;
import com.zhangjun.mbg.mapper.user.UmsAdminMapper;
import com.zhangjun.mbg.mapper.user.UmsResourceMapper;
import com.zhangjun.mbg.model.user.UmsAdmin;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/28 16:10
 * @Version 1.0
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UmsAdminMapper userMapper;

    @Resource
    private UmsResourceMapper resourceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals("")) {
            throw new InternalAuthenticationServiceException("");
        }

        //1、连接数据库，根据用户名查询账号信息
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        UmsAdmin user =userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }

        //2、赋权操作 死的数组
        /**
        List<String> list = new ArrayList<>();
        list.add("select");
        list.add("delete");
         */

        //2、赋权操作 活的数组 从数据库中获取
        List<String> list = resourceMapper.getResourceByUserId(user.getId());
        //3、返回UserDetails对象
        return new LoginUser(user,list);
    }
}
