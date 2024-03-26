package com.rentlink.rentlink.manage_notifications;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestScheduledJob {

    private final NotificationsWebSocketHandler notificationsWebSocketHandler;

    @Scheduled(fixedRate = 600000)
    public void sendEmails() {
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(), "Testowa CRITICAL", "OPIS", Priority.CRITICAL, LocalDateTime.now(), false));
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(), "Testowa HIGH", "OPIS", Priority.HIGH, LocalDateTime.now(), false));
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(), "Testowa MEDIUM", "OPIS", Priority.MEDIUM, LocalDateTime.now(), false));
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(), "TESTOWA LOW", "OPIS", Priority.LOW, LocalDateTime.now(), false));
    }
}
