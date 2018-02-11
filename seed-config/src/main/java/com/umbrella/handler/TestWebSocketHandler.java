package com.umbrella.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket测试Handler
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-11
 */
public class TestWebSocketHandler extends TextWebSocketHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(TestWebSocketHandler.class);
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("socket 连接已建立");
        logger.info("sessionId:" + session.getId()
                + ",localAddr:" + session.getLocalAddress()
                 + ",remoteAddr:" + session.getRemoteAddress()
                 + ",url:" + session.getUri()
        );
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("客户端" + session.getId() + "传来的信息:" + message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("socket 连接已关闭");
        logger.info("sessionId:" + session.getId()
                + ",localAddr:" + session.getLocalAddress()
                + ",remoteAddr:" + session.getRemoteAddress()
                + ",url:" + session.getUri()
        );
    }
}
