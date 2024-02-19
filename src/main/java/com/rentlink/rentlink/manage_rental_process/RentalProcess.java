package com.rentlink.rentlink.manage_rental_process;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "rental_process", schema = "rentlink")
@Getter
@Setter
class RentalProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "rental_option_id")
    private UUID rentalOptionId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RentalProcessStatus status;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ProcessDefinitionDTO definition;

    @Column(name = "current_step_id")
    private UUID currentStepId;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    RentalProcess nullId() {
        this.id = null;
        return this;
    }

    RentalProcess calculateCurrentStepId() {
        definition.steps().sort(Comparator.comparing(ProcessStepDTO::order));
        for (ProcessStepDTO processStepDTO : definition.steps()) {
            boolean missingFields = processStepDTO.inputs().stream()
                    .filter(processDataInputDTO -> !processDataInputDTO.isOptional())
                    .map(ProcessDataInputDTO::value)
                    .anyMatch(Objects::isNull);
            if (missingFields) {
                this.currentStepId = processStepDTO.stepId();
                break;
            }
        }
        return this;
    }

    RentalProcess calculateStatus() {
        boolean missingFields = definition.steps().stream()
                .flatMap(processStepDTO -> processStepDTO.inputs().stream())
                .filter(processDataInputDTO -> !processDataInputDTO.isOptional())
                .map(ProcessDataInputDTO::value)
                .anyMatch(Objects::isNull);
        if (missingFields) {
            this.status = RentalProcessStatus.IN_PROGRESS;
        } else {
            this.status = RentalProcessStatus.COMPLETED;
        }
        return this;
    }
}
