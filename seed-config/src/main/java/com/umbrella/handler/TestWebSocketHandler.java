package com.umbrella.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket测试Handler,Spring WebSocket API
 *
 * @author 011096=>yangyunsen@inner.czy.com
 * @version 1.0
 * @date 2018-02-11
 */
public class TestWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(TestWebSocketHandler.class);
    private static Integer onlineCount = 0;
    private static ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("socket 连接已建立");
        logger.info("sessionId:" + session.getId()
                + ",localAddr:" + session.getLocalAddress()
                + ",remoteAddr:" + session.getRemoteAddress()
                + ",url:" + session.getUri()
        );
        this.addOnlineCount();
        sessions.putIfAbsent(session.getId(),session);
        sendMessageToAllUsers(session, new TextMessage("上线了!"));
        sendOnlineCountToAllUsers();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("客户端" + session.getId() + "传来的信息:" + message);
        sendMessageToAllUsers(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("socket 连接已关闭");
        logger.info("sessionId:" + session.getId()
                + ",localAddr:" + session.getLocalAddress()
                + ",remoteAddr:" + session.getRemoteAddress()
                + ",url:" + session.getUri()
        );
        this.subOnlineCount();
        sessions.remove(session.getId());
        sendMessageToAllUsers(session, new TextMessage("下线了!"));
        sendOnlineCountToAllUsers();
    }

    private synchronized void addOnlineCount() {
        onlineCount ++;
    }

    private synchronized void subOnlineCount() {
        onlineCount --;
    }

    private void sendOnlineCountToAllUsers() throws Exception {
        for (WebSocketSession session : sessions.values()) {
            session.sendMessage(new TextMessage("当前在线用户为:" + onlineCount));
        }
    }

    private void sendMessageToAllUsers(WebSocketSession currentSession, TextMessage message) throws Exception {
        for (WebSocketSession session : sessions.values()) {
            session.sendMessage(new TextMessage("用户:"
                    + currentSession.getId()
                    + ":" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())
                    + ":" + message.getPayload()));
        }
    }
}
