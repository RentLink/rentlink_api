package com.rentlink.rentlink.manage_unit_data;

import com.rentlink.rentlink.manage_rental_options.RentalOptionDTO;
import com.rentlink.rentlink.manage_rental_options.RentalOptionInternalAPI;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UnitManagement implements UnitExternalAPI {

    private final UnitRepository unitRepository;
    private final RentalOptionInternalAPI rentalOptionInternalAPI;
    private final UnitMapper unitMapper;

    @Override
    public UnitDTO getUnit(UUID unitId) {
        UnitDTO unit = unitRepository.findById(unitId).map(unitMapper::map).orElseThrow(UnitNotFoundException::new);
        Set<RentalOptionDTO> rentalOptions = rentalOptionInternalAPI.getRentalOptionsByUnitId(unitId);
        return unit.withRentalOptions(rentalOptions);
    }

    @Override
    public Set<UnitDTO> getUnits(Integer page, Integer pageSize) {
        Stream<Unit> stream;
        if (page != null && pageSize != null) {
            Pageable pageable = PageRequest.of(page, pageSize);
            stream = StreamSupport.stream(unitRepository.findAll(pageable).spliterator(), false);
        } else {
            stream = unitRepository.findAll().stream();
        }

        return stream.map(unitMapper::map)
                .map(unitDTO -> {
                    Set<RentalOptionDTO> rentalOptions = rentalOptionInternalAPI.getRentalOptionsByUnitId(unitDTO.id());
                    return unitDTO.withRentalOptions(rentalOptions);
                })
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public UnitDTO addUnit(UnitDTO unitDTO) {
        UnitDTO result = unitMapper.map(unitRepository.save(unitMapper.map(unitDTO)));
        Set<RentalOptionDTO> rentalOptionResult =
                Optional.ofNullable(unitDTO.rentalOptions()).orElse(Collections.emptySet()).stream()
                        .map(rentalOptionDTO -> rentalOptionDTO.withUnitId(result.id()))
                        .map(rentalOptionInternalAPI::create)
                        .collect(Collectors.toSet());
        return result.withRentalOptions(rentalOptionResult);
    }

    @Override
    public UnitDTO updateUnit(UUID id, UnitDTO unitDTO) {
        Unit unit = unitRepository.findById(id).orElseThrow(UnitNotFoundException::new);
        unitMapper.update(unitDTO, unit);
        UnitDTO result = unitMapper.map(unitRepository.save(unit));
        if (unitDTO.rentalOptions() != null && !unitDTO.rentalOptions().isEmpty()) {
            Set<RentalOptionDTO> rentalOptionResult = unitDTO.rentalOptions().stream()
                    .map(rentalOptionInternalAPI::update)
                    .collect(Collectors.toSet());
            return result.withRentalOptions(rentalOptionResult);
        }
        return result;
    }

    @Override
    public void deleteUnit(UUID unitId) {
        unitRepository.deleteById(unitId);
    }
}
