package com.zhangjun.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhangjun.mbg.model.user.UmsAdmin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/28 15:49
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

   private UmsAdmin user;

   //权限列表
   private List<String> list;//select delete


    //自定义一个权限列表的集合
    @JSONField(serialize=false)
    List<SimpleGrantedAuthority> authorities; //子类

    public LoginUser(UmsAdmin user,List<String> list) {
        this.user = user;
        this.list = list;
    }
    //权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //父类
        if (authorities != null) {
            return authorities;
        }
        authorities = new ArrayList<>();

        for (String item :list)
        {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(item);
            authorities.add(authority);

        };
        return authorities;
    }

    //返回密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //返回用户名
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 账号是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    //账号是否没锁定
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    //账号是否没有超时
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    //账号是否可用
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
