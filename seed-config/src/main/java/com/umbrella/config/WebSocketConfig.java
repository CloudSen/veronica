package com.umbrella.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置类
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-11
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    /**
     * 将java配置的handler 与 url对应起来
     * @param webSocketHandlerRegistry handler注册接口
     * @return void
     * @author [011096]=>yangyunsen@inner.czy.com
     * @date 2018/2/11
    */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

    }
}
