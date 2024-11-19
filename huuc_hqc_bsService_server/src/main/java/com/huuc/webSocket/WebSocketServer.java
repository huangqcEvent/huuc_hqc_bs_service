package com.huuc.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSocket服务
 */
@Slf4j
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    // 存放会话对象
    private static final Map<String, Session> sessionMap = new HashMap<>();

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        log.info("收到来自客户端: {} 的连接", sid);
        sessionMap.put(sid, session);

        // 用户上线时，从 Redis 中读取消息并发送 TODO 此处有问题 redisTemplate为空》》》
        if (redisTemplate != null) {
            log.info("RedisTemplate已注入");
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
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        log.info("收到来自客户端: {} 的信息: {}", sid, message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        log.error("与客户端: {} 断开连接", sid);
        sessionMap.remove(sid);
    }

    /**
     * 群发
     */
    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                // 服务器向客户端发送消息
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                log.error("发送消息失败: {}", e.getMessage(), e);
            }
        }
    }

    /**
     * 向指定sid的用户发送消息
     *
     * @param sid     用户id
     * @param message 要发送的消息
     */
    public void sendToClient(String sid, String message) {
        Session session = sessionMap.get(sid);
        if (session != null && session.isOpen()) {
            try {
                // 服务器向指定客户端发送消息
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                log.error("发送消息失败: {}", e.getMessage(), e);
            }
        } else {
            log.info("客户端 {} 不在线", sid);
            // 用户不在线，将消息存储到Redis
            if (redisTemplate != null) {
                String key = "messages:" + sid;
                redisTemplate.opsForList().rightPush(key, message);
                log.info("消息已存储到Redis: {}", key);
            } else {
                log.error("RedisTemplate为空，无法存储离线消息");
            }
        }
    }
}
