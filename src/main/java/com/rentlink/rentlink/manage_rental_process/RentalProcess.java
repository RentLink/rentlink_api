package com.rentlink.rentlink.manage_rental_process;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ProcessDefinitionDTO definition;

    @Column(name = "current_step_id")
    private UUID currentStepId;

    void nullId() {
        this.id = null;
    }

    void calculateCurrentStepId() {
        AtomicReference<UUID> result = new AtomicReference<>();
        definition.steps().sort(Comparator.comparing(ProcessStepDTO::order));
        for (ProcessStepDTO processStepDTO : definition.steps()) {
            boolean missingFields = processStepDTO.inputs().stream()
                    .filter(processDataInputDTO -> !processDataInputDTO.isOptional())
                    .map(ProcessDataInputDTO::value)
                    .anyMatch(Objects::isNull);
            if (missingFields) {
                result.set(processStepDTO.stepId());
                break;
            }
        }
        this.currentStepId = result.get();
    }
}
