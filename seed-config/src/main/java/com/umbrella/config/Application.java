package com.umbrella.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
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
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@ComponentScan({"com.umbrella.controller","com.umbrella.config"})
public class Application {

    /*@RequestMapping("/")
    public String test() {
        return "hello";
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
