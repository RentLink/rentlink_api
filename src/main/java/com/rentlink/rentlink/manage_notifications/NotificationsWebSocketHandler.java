package com.rentlink.rentlink.manage_notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentlink.rentlink.common.CustomHeaders;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
@Slf4j
public class NotificationsWebSocketHandler extends AbstractWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private final NotificationExternalAPI notificationExternalAPI;

    private final ObjectMapper objectMapper;

    public NotificationsWebSocketHandler(NotificationExternalAPI notificationExternalAPI, ObjectMapper objectMapper) {
        this.notificationExternalAPI = notificationExternalAPI;
        this.objectMapper = objectMapper;
    }

    public void sendNotification(UUID accountId, NotificationDTO message) {
        sessions.forEach((id, session) -> {
            sessions.forEach((key, s) -> {
                if (key.startsWith(accountId.toString())) {
                    try {
                        s.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String accountId =
                Objects.requireNonNull(session.getHandshakeHeaders().get(CustomHeaders.X_USER_HEADER)).stream()
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
        log.info("Session established: {}", session.getId());
        sessions.put(accountId + session.getId(), session);
        notificationExternalAPI.getNotifications(UUID.fromString(accountId)).forEach(notificationDTO -> {
            try {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(notificationDTO)));
            } catch (RuntimeException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String accountId =
                Objects.requireNonNull(session.getHandshakeHeaders().get(CustomHeaders.X_USER_HEADER)).stream()
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
        log.info("Session closed: {}", session.getId());
        sessions.remove(accountId + session.getId());
    }


}
