package com.umbrella.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @date 2018-6-1
 * @author CloudSen
 * @apiNote Spring Data Redis 配置类
 */
@Configuration
public class RedisConfig {

    private static String HOST_NAME = "192.168.114.128";
    private static String PASSWORD = "cloudsen";

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {

        RedisPassword redisPassword = RedisPassword.of(PASSWORD);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName(HOST_NAME);
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword(redisPassword);
        return redisStandaloneConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        return new JedisConnectionFactory(redisStandaloneConfiguration());
    }

    @Bean
    public RedisTemplate<String, String> stringRedisTemplate() {

        RedisTemplate<String, String> stringRedisTemplate = new RedisTemplate<>();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        stringRedisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }
}
