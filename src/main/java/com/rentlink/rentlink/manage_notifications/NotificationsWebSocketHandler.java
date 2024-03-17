package com.rentlink.rentlink.manage_notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentlink.rentlink.common.CustomHeaders;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
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

    private final NotificationInternalAPI notificationInternalAPI;

    private final ObjectMapper objectMapper;

    public NotificationsWebSocketHandler(NotificationInternalAPI notificationInternalAPI, ObjectMapper objectMapper) {
        this.notificationInternalAPI = notificationInternalAPI;
        this.objectMapper = objectMapper;
    }

    public void sendNotification(UUID accountId, NotificationDTO message) {
        notificationInternalAPI.createNotification(accountId, message);
        sessions.forEach((id, session) -> {
            if (id.startsWith(accountId.toString())) {
                try {
                    session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        sessions.forEach((id, s) -> {
            try {
                if (s.getId().endsWith(session.getId())) {
                    UUID accountId = UUID.fromString(id.split(":")[0]);
                    Set<UUID> notificationIds = Arrays.stream(
                                    message.getPayload().split(","))
                            .filter(msg -> !msg.isEmpty())
                            .filter(msg -> !msg.isBlank())
                            .map(UUID::fromString)
                            .collect(Collectors.toSet());
                    notificationInternalAPI.markAsReceived(accountId, notificationIds);
                }
            } catch (Exception e) {
                log.error("Error while handling text message", e);
            }
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String accountId =
                Objects.requireNonNull(session.getHandshakeHeaders().get(CustomHeaders.X_USER_HEADER)).stream()
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
        log.info("Session established: {}", session.getId());
        sessions.put(accountId + ":" + session.getId(), session);
        notificationInternalAPI.getNotifications(UUID.fromString(accountId)).forEach(notificationDTO -> {
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
        sessions.remove(accountId + ":" + session.getId());
    }
}
