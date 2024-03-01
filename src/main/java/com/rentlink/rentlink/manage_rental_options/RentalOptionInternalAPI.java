package com.rentlink.rentlink.manage_rental_options;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RentalOptionInternalAPI {

    Set<RentalOptionDTO> getRentalOptionsByUnitId(UUID unitId, UUID accountId);

    Optional<RentalOptionDTO> getRentalOptionsById(UUID id, UUID accountId);

    RentalOptionDTO upsert(RentalOptionDTO rentalOptionDTO, UUID unitId, UUID accountId);
}
