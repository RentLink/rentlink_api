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
    public List<NotificationDTO> getNotifications() {
        return notificationRepository.findAll().stream()
                .filter(notification -> !notification.getReceived())
                .map(notificationMapper::map)
                .sorted(Comparator.comparing(NotificationDTO::createdAt)
                        .thenComparing(NotificationDTO::priority)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void markAsReceived(Set<UUID> notificationIds) {
        notificationRepository.markAsReceived(notificationIds);
    }

    @Transactional
    @Override
    public void createNotification(NotificationDTO notificationDTO) {
        Notification notification = notificationMapper.map(notificationDTO);
        notificationRepository.save(notification);
    }
}
