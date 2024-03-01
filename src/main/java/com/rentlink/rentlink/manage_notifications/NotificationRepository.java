package com.rentlink.rentlink.manage_notifications;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    Stream<Notification> findByAccountIdAndReceived(UUID accountId, boolean received);

    @Query(
            value =
                    "UPDATE Notification SET received = true WHERE id IN :ids AND received = false AND account_id = :accountId")
    @Modifying
    int markAsReceived(UUID accountId, Set<UUID> ids);
}
