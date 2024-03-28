package com.rentlink.rentlink.manage_incoming_events;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incoming_event", schema = "rentlink")
@Getter
@Setter
public class IncomingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "unit_id")
    private UUID unitId;

    private String title;
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private IncomingEventType type;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
