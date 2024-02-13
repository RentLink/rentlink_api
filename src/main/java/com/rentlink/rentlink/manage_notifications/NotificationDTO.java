package com.rentlink.rentlink.manage_notifications;

import java.time.LocalDate;
import java.util.UUID;

public record NotificationDTO(
        UUID id, String title, String description, Priority priority, LocalDate dueDate, Boolean received) {

    public static NotificationDTO createNewNotification(
            String title, String description, LocalDate dueDate, Priority priority) {
        return new NotificationDTO(null, title, description, priority, dueDate, false);
    }
}
