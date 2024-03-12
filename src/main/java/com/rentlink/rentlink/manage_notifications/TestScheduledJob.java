package com.rentlink.rentlink.manage_notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TestScheduledJob {

    private final NotificationsWebSocketHandler notificationsWebSocketHandler;

    @Scheduled(fixedRate = 5000)
    public void sendEmails() {
        notificationsWebSocketHandler.sendNotification(UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"), new NotificationDTO(UUID.randomUUID(), "Test message", "Test message", Priority.HIGH, LocalDateTime.now(), false));
    }
}
