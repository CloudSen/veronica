package com.umbrella.config;

import com.umbrella.handler.TestWebSocketHandler;
import com.umbrella.interceptor.TestWebSocketInterception;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * WebSocket配置类，基于Spring WebSocket Api
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-11
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    /**
     * 将java配置的handler 与 url 映射起来，并能添加拦截器
     *
     * @param registry handler注册接口
     * @return void
     * @author [011096]=>yangyunsen@inner.czy.com
     * @date 2018/2/11
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(testHandler(), "/websocket")
                .addInterceptors(testInterception());
    }

    /**
     * 配置容器
     *
     * @return org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean
     * @author [011096]=>yangyunsen@inner.czy.com
     * @date 2018/2/13
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

    @Bean
    public TestWebSocketHandler testHandler() {
        return new TestWebSocketHandler();
    }

    @Bean
    public TestWebSocketInterception testInterception() {
        return new TestWebSocketInterception();
    }
}
