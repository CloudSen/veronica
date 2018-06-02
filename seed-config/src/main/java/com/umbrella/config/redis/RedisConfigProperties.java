package com.umbrella.config.redis;

/**
 * @author CloudSen
 * @date 2018-6-1
 * @apiNote Redis配置参数类
 */
class RedisConfigProperties {

    static final String HOST = "192.168.114.128";
    static final int PORT = 6379;
    static final String PASSWORD = "cloudsen";
    static final int DATABASE = 0;
    static final int MIN_IDLE = 0;
    static final int MAX_IDLE = 5;
    static final int MAX_TOTAL = 10;
    static final int MAX_WAIT_MILLIS = 3000;
    static final int TIMEOUT = 3000;

}
