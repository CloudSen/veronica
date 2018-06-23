package com.umbrella.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author CloudSen
 * @date 2018-6-1
 * @apiNote Spring Data Redis 配置类
 */
@Configuration
public class RedisConfig {

    /**
     * 配置Jedis连接池
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最小空闲连接数
        jedisPoolConfig.setMinIdle(RedisConfigProperties.MIN_IDLE);
        // 最大空闲连接数
        jedisPoolConfig.setMaxIdle(RedisConfigProperties.MAX_IDLE);
        // 最大连接数
        jedisPoolConfig.setMaxTotal(RedisConfigProperties.MAX_TOTAL);
        // 获取连接时的最大等待毫秒数。如果超时就抛异常, 小于零:阻塞不确定的时间，默认为-1
        jedisPoolConfig.setMaxWaitMillis(RedisConfigProperties.MAX_WAIT_MILLIS);
        return jedisPoolConfig;
    }

    /**
     * 使用Spring-Data-Redis为单节点模式,配置Redis服务器基本信息
     */
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {

        RedisPassword redisPassword = RedisPassword.of(RedisConfigProperties.PASSWORD);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(RedisConfigProperties.DATABASE);
        redisStandaloneConfiguration.setHostName(RedisConfigProperties.HOST);
        redisStandaloneConfiguration.setPort(RedisConfigProperties.PORT);
        redisStandaloneConfiguration.setPassword(redisPassword);
        return redisStandaloneConfiguration;
    }

    /**
     * Spring-Data-Redis 2.0之后的新特性，设置要用到的连接池，Jedis连接工厂要用到
     */
    @Bean
    public JedisClientConfiguration jedisClientConfiguration() {

        // 得到连接池客户端配置接口
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        // 设置连接池为上方的jedisPoolConfig
        jedisPoolingClientConfigurationBuilder.poolConfig(jedisPoolConfig());
        return jedisPoolingClientConfigurationBuilder.and().build();
    }

    /**
     * Jedis连接工厂
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        return new JedisConnectionFactory(redisStandaloneConfiguration(), jedisClientConfiguration());
    }

    /**
     * Spring-Data-Redis 模板，使用它来与Redis交互
     */
    @Bean
    public RedisTemplate<String, String> stringRedisTemplate() {

        RedisTemplate<String, String> stringRedisTemplate = new RedisTemplate<>();
        stringRedisTemplate.setConnectionFactory(new JedisConnectionFactory());
        // 设置序列化
        stringRedisTemplate.setDefaultSerializer(new StringRedisSerializer());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }
}
