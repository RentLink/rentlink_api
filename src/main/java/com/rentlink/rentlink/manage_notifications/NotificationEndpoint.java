package com.rentlink.rentlink.manage_notifications;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationEndpoint {

    private final NotificationExternalAPI notificationExternalAPI;

    @GetMapping("/")
    List<NotificationDTO> getUnits() {
        return notificationExternalAPI.getNotifications();
    }

    @PutMapping("/{notificationId}/mark-as-received")
    void markAsReceived(@PathVariable UUID notificationId) {
        notificationExternalAPI.markAsReceived(Set.of(notificationId));
    }

    @PutMapping("/bulk/mark-as-received")
    void bulkMarkAsReceived(BulkMarkAsReceived bulkMarkAsReceived) {
        notificationExternalAPI.markAsReceived(bulkMarkAsReceived.notificationIds());
    }
}
