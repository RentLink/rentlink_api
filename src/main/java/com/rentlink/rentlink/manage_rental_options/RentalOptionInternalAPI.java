package com.rentlink.rentlink.manage_rental_options;

import java.util.Set;
import java.util.UUID;

public interface RentalOptionInternalAPI {

    Set<RentalOptionDTO> getRentalOptionsByUnitId(UUID unitId);

    RentalOptionDTO create(RentalOptionDTO rentalOptionDTO);

    RentalOptionDTO update(RentalOptionDTO rentalOptionDTO);
}
