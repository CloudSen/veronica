package com.umbrella.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Map;

/**
 * Websocket测试拦截器
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-11
 */
public class TestWebSocketInterception implements HandshakeInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(TestWebSocketInterception.class);
    /**
     * 客户端与服务器握手前拦截
     * @param serverHttpRequest 请求对象
     * @param serverHttpResponse 回应对象
     * @param webSocketHandler 处理器
     * @param map 传来的attributes
     * @return boolean
     * @author [011096]=>yangyunsen@inner.czy.com
     * @date 2018/2/11
    */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,
                                   ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler,
                                   Map<String, Object> map) throws Exception {
        HttpSession httpSession = this.getSession(serverHttpRequest);
        if (httpSession != null) {
            map.put("sessionId",httpSession.getId());
            logger.info("socket握手拦截器启动，握手前:");
            logger.info("sessionId:" + httpSession.getId()  + "," + httpSession.getAttributeNames().toString());
        }
        logger.info("socket握手拦截器启动，握手前: session == null");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest,
                               ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler,
                               Exception e) {

    }

    /**
     * 获取ServletHttpRequest
     * @param serverHttpRequest  ServerHttpRequest
     * @return javax.servlet.http.HttpSession
     * @author [011096]=>yangyunsen@inner.czy.com
     * @date 2018/2/11
    */
    private HttpSession getSession(ServerHttpRequest serverHttpRequest){
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            return request.getServletRequest().getSession();
        } else {
            return null;
        }
    }
}
