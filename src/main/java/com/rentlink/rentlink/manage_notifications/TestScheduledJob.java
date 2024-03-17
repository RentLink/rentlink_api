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

    @Scheduled(fixedRate = 60000)
    public void sendEmails() {
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(),
                        "You dick",
                        "Its critical to say, u r dick",
                        Priority.CRITICAL,
                        LocalDateTime.now(),
                        false));
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(),
                        "You dick",
                        "Its still very important to say, u r dick",
                        Priority.HIGH,
                        LocalDateTime.now(),
                        false));
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(),
                        "It's at most average priority",
                        "Nevertheless you re still a dick",
                        Priority.MEDIUM,
                        LocalDateTime.now(),
                        false));
        notificationsWebSocketHandler.sendNotification(
                UUID.fromString("f6a401de-8408-45e0-b083-49eb9723b573"),
                new NotificationDTO(
                        UUID.randomUUID(),
                        "Not important message",
                        "U DICK",
                        Priority.LOW,
                        LocalDateTime.now(),
                        false));
    }
}
