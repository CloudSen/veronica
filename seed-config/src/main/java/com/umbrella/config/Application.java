package com.umbrella.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Description: Spring Boot 启动类
 * 注解SpringBootApplication中的scanBasePackages参数相当于ComponentScan注解，配置了自动扫描。
 * </p>
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-09
 * @version: 1.0
 */
@SpringBootApplication(
        scanBasePackages = {"com.umbrella"},
        exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
        )
@MapperScan("com.umbrella.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
