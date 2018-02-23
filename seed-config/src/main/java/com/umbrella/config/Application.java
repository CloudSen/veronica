package com.umbrella.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description: Spring Boot 启动类
 * </p>
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-09
 * @version: 1.0
 */
@SpringBootApplication
@RestController
@ComponentScan({"com.umbrella"})
@MapperScan("com.umbrella.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
