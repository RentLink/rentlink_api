package com.rentlink.rentlink.manage_rental_options;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.UUID;

@RecordBuilder
public record RentalOptionDTO(UUID id, UUID unitId, String name) implements RentalOptionDTOBuilder.With {}
