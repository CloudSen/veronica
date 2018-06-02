package com.umbrella.controller.learnspringdataredis;

import com.umbrella.service.learnspringdataredis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author CloudSen
 * @date 2018-6-1
 * @apiNote Spring Data Redis 控制器
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestCtrl {

    private RedisService redisService;

    @Autowired
    public RedisTestCtrl(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/add/{key}/{value}")
    public void add(@PathVariable("key") final String key, @PathVariable("value") final String value) {
        this.redisService.add(key, value);
    }

    @GetMapping("/del/{key}")
    public void add(@PathVariable("key") final String key) {
        this.redisService.delete(key);
    }

    @GetMapping("/update/{key}/{value}")
    public void update(@PathVariable("key") final String key, @PathVariable("value") final String value) {
        this.redisService.update(key, value);
    }

    @GetMapping("/findAll")
    public Set<String> findAll() {
        return this.redisService.findAll();
    }

    @GetMapping("/find/{key}")
    public String find(@PathVariable("key") final String key) {
        return this.redisService.findByKey(key);
    }
}
