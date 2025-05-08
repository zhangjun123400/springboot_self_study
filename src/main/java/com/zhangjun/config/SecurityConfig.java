package com.zhangjun.config;

import com.zhangjun.filter.JwtAuthenticationTokenFilter;
import com.zhangjun.handler.AnonymousAuthenticationHandler;
import com.zhangjun.handler.CustomerAccessDeniedHandler;
import com.zhangjun.handler.LoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/28 16:59
 * @Version 1.0
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //认证用户无权限访问资源的处理器
    @Autowired
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    //客户端进行认证数据的提交时出现异常，或者是匿名用户访问受限资源的处理器
    @Autowired
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;

    //用户认证校验失败处理器
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登陆时需要调用AuthenticationManager.authenticate执行一次校验
     * @param config
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    //登陆请求放行

    /**
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //配置关闭csrf机制
        http.csrf(csrf -> csrf.disable());

        //用户人认证校验失败处理器loginFailureHandler
        http.formLogin(configure -> configure.failureHandler(loginFailureHandler));

        //STATELESS(无状态)：表示应用层程序是无状态的，不会创建会话
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        List<String> ignordList=ignoreUrlsConfig.getUrls();

        //配置请求的拦截方式
        http.authorizeHttpRequests(auth ->
            auth.requestMatchers(ignordList.toArray(new String[0]))
                    .permitAll()
                    .anyRequest()
                    .authenticated()
        );

        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //添加自定义的异常处理器
        http.exceptionHandling(exception -> {
            exception.accessDeniedHandler(customerAccessDeniedHandler)
                     .authenticationEntryPoint(anonymousAuthenticationHandler);
        });
        return http.build();
    }



}
