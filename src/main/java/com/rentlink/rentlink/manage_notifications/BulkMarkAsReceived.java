package com.rentlink.rentlink.manage_notifications;

import java.util.Set;
import java.util.UUID;

public record BulkMarkAsReceived(Set<UUID> notificationIds) {}
