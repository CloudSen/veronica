package com.umbrella.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;

/**
 * Spring Social 配置类
 * 开关: @EnableSocial
 * 配置ConnectionFactory和ConnectionRepository
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-09
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

}
