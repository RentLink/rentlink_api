package com.rentlink.rentlink.manage_email_inbound;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "awaiting_documents_task", schema = "rentlink")
@Getter
@Setter
@NoArgsConstructor
public class AwaitingDocumentTask {

    public AwaitingDocumentTask(
            UUID accountId, UUID rentalProcessId, String email, UUID recognitionCode, AwaitingTaskStatus status) {
        this.accountId = accountId;
        this.rentalProcessId = rentalProcessId;
        this.email = email;
        this.recognitionCode = recognitionCode;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "rental_process_id")
    private UUID rentalProcessId;

    private String email;

    @Column(name = "recognition_code")
    private UUID recognitionCode;

    @Enumerated(EnumType.STRING)
    private AwaitingTaskStatus status;
}
