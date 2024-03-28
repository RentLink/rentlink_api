package com.rentlink.rentlink.manage_incoming_events;

import java.time.LocalDateTime;
import java.util.UUID;

public record IncomingEventDTO(
        UUID id,
        UUID accountId,
        UUID unitId,
        String title,
        String description,
        IncomingEventType type,
        LocalDateTime dateTime) {

    public static IncomingEventDTO createMeeting(
            UUID accountId, UUID unitId, String title, String description, LocalDateTime dateTime) {
        return new IncomingEventDTO(
                UUID.randomUUID(), accountId, unitId, title, description, IncomingEventType.MEETING, dateTime);
    }
}
