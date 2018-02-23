package com.umbrella.controller.learnwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * socket测试类,基于JAVA AIP FOR WEBSOCKET
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-22
 */
@ServerEndpoint(value = "/websocket")
public class SocketJavaApi {

    private Session session;
    private static final Logger logger = LoggerFactory.getLogger(SocketJavaApi.class);

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        logger.info("建立socket连接");
    }

    @OnClose
    public void onClose() {
        logger.info("关闭socket连接");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("socket收到消息：" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("socket发生错误");
    }
}
