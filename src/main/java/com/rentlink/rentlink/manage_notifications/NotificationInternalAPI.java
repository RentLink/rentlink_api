package com.rentlink.rentlink.manage_notifications;

import java.util.UUID;

public interface NotificationInternalAPI {

    void createNotification(UUID accountId, NotificationDTO notificationDTO);
}
