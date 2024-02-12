package com.rentlink.rentlink.manage_notifications;

import java.util.Set;
import java.util.UUID;

public interface NotificationExternalAPI {

    Set<NotificationDTO> getNotifications();

    void markAsReceived(Set<UUID> notificationIds);
}
