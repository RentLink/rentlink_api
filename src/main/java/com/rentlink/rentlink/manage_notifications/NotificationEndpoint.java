package com.rentlink.rentlink.manage_notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationEndpoint {

    private final NotificationManagement notificationManagement;

    @GetMapping("/")
    Set<NotificationDTO> getUnits() {
        return notificationManagement.getNotifications();
    }

    @PutMapping("/{notificationId}/mark-as-received")
    void markAsReceived(@PathVariable UUID notificationId) {
        notificationManagement.markAsReceived(Set.of(notificationId));
    }

    @PutMapping("/bulk/mark-as-received")
    void bulkMarkAsReceived(BulkMarkAsReceived bulkMarkAsReceived) {
        notificationManagement.markAsReceived(bulkMarkAsReceived.notificationIds());
    }
}
