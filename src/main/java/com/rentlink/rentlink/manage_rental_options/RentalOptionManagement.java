package com.rentlink.rentlink.manage_rental_options;

import java.util.Optional;
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
    public Set<RentalOptionDTO> getRentalOptionsByUnitId(UUID unitId, UUID accountId) {
        return rentalOptionRepository
                .findRentalOptionsByAccountIdAndUnitId(accountId, unitId)
                .map(rentalOptionMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public RentalOptionDTO upsert(RentalOptionDTO rentalOptionDTO, UUID unitId, UUID accountId) {
        if (rentalOptionDTO.id() != null) {
            RentalOption rentalOption = rentalOptionRepository
                    .findByAccountIdAndId(accountId, rentalOptionDTO.id())
                    .orElseThrow(RentalOptionFoundException::new);
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

    @Override
    public Optional<RentalOptionDTO> getRentalOptionsById(UUID id, UUID accountId) {
        return rentalOptionRepository.findByAccountIdAndId(accountId, id).map(rentalOptionMapper::map);
    }
}
