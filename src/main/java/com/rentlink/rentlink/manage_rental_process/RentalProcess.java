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
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

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

    private String name;

    @Column(name = "definition_type")
    @Enumerated(EnumType.STRING)
    private ProcessDefinitionType definitionType;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<ProcessStepDTO> steps;

    @Column(name = "current_step_id")
    private UUID currentStepId;
}
