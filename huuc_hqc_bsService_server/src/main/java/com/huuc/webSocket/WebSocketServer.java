package com.huuc.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务
 */
@Slf4j
@Component
public class WebSocketServer implements WebSocketHandler {

    // 存放会话对象
    private static final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        if (redisTemplate == null) {
            log.error("RedisTemplate未注入，请检查配置");
        } else {
            log.info("RedisTemplate已注入");
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sid = session.getUri().toString().split("/")[4];
        log.info("收到来自客户端: {} 的连接", sid);
        sessionMap.put(sid, session);

        // 用户上线时，从 Redis 中读取消息并发送
        readAndSendMessagesFromRedis(sid);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String sid = session.getUri().toString().split("/")[4];
        log.info("收到来自客户端: {} 的信息: {}", sid, message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("与客户端: {} 发生传输错误: {}", session.getId(), exception.getMessage(), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sid = session.getUri().toString().split("/")[4];
        log.error("与客户端: {} 断开连接", sid);
        sessionMap.remove(sid);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 群发
     */
    public void sendToAllClient(String message) {
        sessionMap.values().forEach(session -> {
            try {
                // 服务器向客户端发送消息
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("发送消息失败: {}", e.getMessage(), e);
            }
        });
    }

    /**
     * 向指定sid的用户发送消息
     *
     * @param sid     用户id
     * @param message 要发送的消息
     */
    public void sendToClient(String sid, String message) {
        WebSocketSession session = sessionMap.get(sid);
        if (session != null && session.isOpen()) {
            try {
                // 服务器向指定客户端发送消息
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("发送消息失败: {}", e.getMessage(), e);
            }
        } else {
            log.info("客户端 {} 不在线", sid);
            storeMessageToRedis(sid, message);
        }
    }

    /**
     * 从 Redis 中读取消息并发送
     *
     * @param sid 用户id
     */
    private void readAndSendMessagesFromRedis(String sid) {
        if (redisTemplate != null) {
            String key = "messages:" + sid;
            log.info("尝试从Redis中读取键: {}", key);
            List<Object> messages = redisTemplate.opsForList().range(key, 0, -1);
            if (messages != null && !messages.isEmpty()) {
                log.info("从Redis中读取到 {} 条消息", messages.size());
                for (Object msg : messages) {
                    sendToClient(sid, msg.toString());
                }
                // 清除Redis中的消息
                redisTemplate.delete(key);
                log.info("清除Redis中的消息: {}", key);
            } else {
                log.info("Redis中没有找到离线消息");
            }
        } else {
            log.error("RedisTemplate为空，无法接收离线消息");
        }
    }

    /**
     * 将消息存储到 Redis
     *
     * @param sid     用户id
     * @param message 要存储的消息
     */
    private void storeMessageToRedis(String sid, String message) {
        if (redisTemplate != null) {
            String key = "messages:" + sid;
            redisTemplate.opsForList().rightPush(key, message);
            log.info("消息已存储到Redis: {}", key);
        } else {
            log.error("RedisTemplate为空，无法存储离线消息");
        }
    }
}