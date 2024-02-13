package com.rentlink.rentlink.manage_notifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Query(value = "UPDATE Notification SET received = true WHERE id IN :ids")
    @Modifying
    int markAsReceived(Set<UUID> ids);
}
