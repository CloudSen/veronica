package com.umbrella.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>
 * Description: 自定义MVC配置
 * </p>
 *
 * @author: 011096=>yangyunsen@inner.czy.com
 * @date: 2018-01-09
 * @version: 1.0
 */

public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/**");
        super.addResourceHandlers(registry);
    }
}
