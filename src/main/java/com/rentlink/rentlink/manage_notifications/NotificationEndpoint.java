package com.rentlink.rentlink.manage_notifications;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationEndpoint {

    private final NotificationExternalAPI notificationExternalAPI;

    @GetMapping("/")
    List<NotificationDTO> getNotifications(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return notificationExternalAPI.getNotifications(accountId);
    }

    @PutMapping("/{notificationId}/mark-as-received")
    void markAsReceived(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID notificationId) {
        notificationExternalAPI.markAsReceived(accountId, Set.of(notificationId));
    }

    @PutMapping("/bulk/mark-as-received")
    void bulkMarkAsReceived(
            @RequestHeader(value = X_USER_HEADER) UUID accountId, BulkMarkAsReceived bulkMarkAsReceived) {
        notificationExternalAPI.markAsReceived(accountId, bulkMarkAsReceived.notificationIds());
    }
}
