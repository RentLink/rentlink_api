package com.rentlink.rentlink.manage_rental_process;

import java.util.UUID;

record RentalProcessDTO(UUID id, UUID rentalOptionId, ProcessDefinitionDTO definition, UUID currentStepId) {}
