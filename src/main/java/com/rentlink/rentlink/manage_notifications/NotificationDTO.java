package com.rentlink.rentlink.manage_notifications;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDTO(
        UUID id, String title, String description, Priority priority, LocalDateTime createdAt, Boolean received) {

    public static NotificationDTO createNewNotification(String title, String description, Priority priority) {
        return new NotificationDTO(null, title, description, priority, LocalDateTime.now(), false);
    }
}
