package com.rentlink.rentlink.manage_email_comms;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "email_order_outbox", schema = "rentlink")
@Getter
@Setter
class EmailOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    private String email;
    private String subject;
    private String message;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> files;

    @Enumerated(EnumType.STRING)
    private EmailOrderStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "send_at")
    private LocalDateTime sentAt;

    @Column(name = "error_message")
    private String errorMessage;
}
