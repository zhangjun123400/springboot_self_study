package com.zhangjun.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * @Author zhangjun
 * @Date 2025/4/22 17:26
 * @Version 1.0
 */
@Configuration
@MapperScan({"com.zhangjun.mbg.mapper","com.zhangjun.mbg.mapper.user", "com.zhangjun.dao"})
public class MyBatisConfig {
}
