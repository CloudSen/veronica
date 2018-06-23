package com.umbrella.service.learnspringdataredis.impl;

import com.umbrella.service.learnspringdataredis.RedisTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author CloudSen
 * @date 2018-6-1
 * @apiNote Spring Data Redis -- String 数据操作
 */
@Service
public class RedisTestServiceImpl implements RedisTestService {

    private RedisTemplate<String, String> stringRedisTemplate;
    private ValueOperations<String, String> opsForString;
    private final Logger logger = LoggerFactory.getLogger(RedisTestServiceImpl.class);

    @Autowired
    public RedisTestServiceImpl(RedisTemplate<String, String> stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.opsForString = this.stringRedisTemplate.opsForValue();
    }

    @Override
    public void add(String key, String value) {
        logger.info("test");
        this.opsForString.set(key, value);
    }

    @Override
    public void delete(String key) {
        this.stringRedisTemplate.delete(key);
    }

    @Override
    public void update(String key, String value) {
        this.opsForString.getAndSet(key, value);
    }

    @Override
    public Set<String> findAll() {
        return this.stringRedisTemplate.keys("*");
    }

    @Override
    public String findByKey(String key) {
        return this.opsForString.get(key);
    }
}
