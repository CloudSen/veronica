[TOC]
# SpringBoot 整合Spring-Data-Redis
> 主要是Jedis的配置，Lettuce 不熟；  
JedisConnectFactory中的setUsePool()方法已弃用，配置连接池通过JedisClientConfiguration；  

## jar包
- spring-data-redis 2.0.X
- redis-clients-jedis
## 必知的类/接口
- RedisStandaloneConfiguration: 最基础的单节点Redis配置类；
- RedisSentinelConfiguration: 对Sentinel进行哨兵配置，用于集群；
- RedisClusterConfiguration: 可以说是sentinel和主从模式的结合体；
- JedisConnectionFactory: Jedis提供的连接工厂，传入配置类；
- JedisClientConfiguration: Redis客户端针对Jedis的配置接口，配置SSLSOCKET，连接池；
- RedisTemplate<T, T>: Redis模板类,是我们最终要使用的类，需要设置连接工厂
    - 通过此类可以进行Redis的基本command命令，如delete,dump,keys,type等；
    - 通过此类可以得到操作复杂类型的对象，如opsForHash(),opsForList(),opsForValue()等。
## 配置 
### 非连接池方式（单一节点）
#### 配置方法一：使用propertiesSource搭配Java配置类的方式
在SpringBoot的 `application.properties` 文件中做如下配置：
```yaml
spring:
  redis:
    host: # redis服务器IP
    port: # 端口号，默认6379
    password:  # redis验证密码
    database: # 数据库的index,默认0
```  
新建一个Redis配置类：  
RedisConfig.java  
```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, String> stringRedisTemplate() {

        RedisTemplate<String, String> stringRedisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        stringRedisTemplate.setConnectionFactory(new JedisConnectionFactory());
        // 设置序列化
        stringRedisTemplate.setDefaultSerializer(new StringRedisSerializer());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }
}
```  
#### 配置方法二：只使用Java类进行配置【推荐】

新建一个Redis参数类：  
RedisProperties.java  
```java
public class RedisProperties {

    public static final String HOST = "192.168.114.128";
    public static final int PORT = 6379;
    public static final String PASSWORD = "cloudsen";
    public static final int DATABASE = 0;
}
```
新建一个Redis配置类：  
RedisConfig.java  
```java
@Configuration
public class RedisConfig {

    // 单个Redis连接
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {

        RedisPassword redisPassword = RedisPassword.of(RedisProperties.PASSWORD);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(RedisProperties.DATABASE);
        redisStandaloneConfiguration.setHostName(RedisProperties.HOST);
        redisStandaloneConfiguration.setPort(RedisProperties.PORT);
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
```  
### 连接池方式（单一节点）
重点是使用`RedisStandaloneConfiguration`和`JedisClientConfiguration`来配置。  
`RedisStandaloneConfiguration`配置了Redis服务器的IP、端口等基本信息。  
`JedisClientConfiguration`使用其中的内部接口`JedisPoolingClientConfigurationBuilder`配置了自定义连接池。  
  
RedisConfig.java  
```java
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
     * 使用Spring-Data-Redis为单节点模式
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
```
### 连接池方式 (多节点，sentinel模式)

### 连接池方式 (多节点，cluster模式)

# 应用