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
    public RentalOptionDTO upsert(RentalOptionDTO rentalOptionDTO, UUID unitId) {
        if (rentalOptionDTO.id() != null) {
            RentalOption rentalOption =
                    rentalOptionRepository.findById(rentalOptionDTO.id()).orElseThrow(RentalOptionFoundException::new);
            rentalOptionMapper.update(rentalOptionDTO, rentalOption);
            rentalOption.setUnitId(unitId);
            return rentalOptionMapper.map(rentalOptionRepository.save(rentalOption));
        } else {
            RentalOption rentalOption = rentalOptionMapper.map(rentalOptionDTO);
            rentalOption.setUnitId(unitId);
            RentalOption saved = rentalOptionRepository.save(rentalOption);
            return rentalOptionMapper.map(saved);
        }
    }
}
