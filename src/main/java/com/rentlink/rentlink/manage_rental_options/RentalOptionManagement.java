package com.rentlink.rentlink.manage_rental_options;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalOptionManagement implements RentalOptionInternalAPI {

    private final RentalOptionRepository rentalOptionRepository;

    private final RentalOptionMapper rentalOptionMapper;

    @Override
    public Set<RentalOptionDTO> getRentalOptionsByUnitId(UUID unitId) {
        return rentalOptionRepository.findRentalOptionByUnitId(unitId).stream()
                .map(rentalOptionMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public RentalOptionDTO create(RentalOptionDTO rentalOptionDTO, UUID unitId) {
        RentalOption rentalOption = rentalOptionRepository.save(rentalOptionMapper.map(rentalOptionDTO));
        rentalOption.setUnitId(unitId);
        return rentalOptionMapper.map(rentalOption);
    }

    @Override
    public RentalOptionDTO update(RentalOptionDTO rentalOptionDTO, UUID unitId) {
        RentalOption rentalOption =
                rentalOptionRepository.findById(rentalOptionDTO.id()).orElseThrow(RentalOptionFoundException::new);
        rentalOption.setUnitId(unitId);
        rentalOptionMapper.update(rentalOptionDTO, rentalOption);
        return rentalOptionMapper.map(rentalOptionRepository.save(rentalOption));
    }
}
