package com.umbrella.service.learnspringdataredis;

import java.util.Set;

/**
 * @author CloudSen
 * @date 2018-6-1
 * @apiNote Spring Data Redis -- String 数据操作
 */
public interface RedisTestService {

    /**
     * 新增数据
     * @param key 新增的键
     * @param value 新增的String类型数据
     */
    void add(String key, String value);

    /**
     * 删除某个键
     * @param key 要删除的键
     */
    void delete(String key);

    /**
     * 更新某个数据
     * @param key 更新的键
     * @param value 更新的String类型数据
     */
    void update(String key, String value);

    /**
     * 查询所有键
     * @return Set<String> 所有键
     */
    Set<String> findAll();

    /**
     * 查询某个键对应的值
     * @param key 要查询的键
     * @return String 键对应的值
     */
    String findByKey(String key);
}
