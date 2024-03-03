package com.rentlink.rentlink.manage_notifications;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class NotificationManagement implements NotificationExternalAPI, NotificationInternalAPI {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    @Transactional(readOnly = true)
    @Override
    public List<NotificationDTO> getNotifications(UUID accountId) {
        return notificationRepository
                .findByAccountIdAndReceived(accountId, false)
                .filter(notification -> !notification.getReceived())
                .map(notificationMapper::map)
                .sorted(Comparator.comparing(NotificationDTO::createdAt)
                        .thenComparing(NotificationDTO::priority)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void markAsReceived(UUID accountId, Set<UUID> notificationIds) {
        notificationRepository.markAsReceived(accountId, notificationIds);
    }

    @Transactional
    @Override
    public void createNotification(UUID accountId, NotificationDTO notificationDTO) {
        Notification notification = notificationMapper.map(notificationDTO);
        notification.setAccountId(accountId);
        notificationRepository.save(notification);
    }
}
