package com.umbrella.config;

import net.gplatform.spring.social.weibo.connect.WeiboConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

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
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfigurer, Environment environment) {
        cfConfigurer.addConnectionFactory(new WeiboConnectionFactory(
                environment.getProperty("weibo.consumerKey"),
                environment.getProperty("weibo.consumerSecret")
        ));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return null;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}
