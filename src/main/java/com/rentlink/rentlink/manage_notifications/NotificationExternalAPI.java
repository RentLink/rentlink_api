package com.rentlink.rentlink.manage_notifications;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface NotificationExternalAPI {

    List<NotificationDTO> getNotifications(UUID accountId);

    void markAsReceived(UUID accountId, Set<UUID> notificationIds);
}
