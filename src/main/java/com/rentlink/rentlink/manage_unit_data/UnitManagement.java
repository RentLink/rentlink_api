package com.rentlink.rentlink.manage_unit_data;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UnitManagement implements UnitExternalAPI {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    public UnitDTO getUnit(UUID unitId) {
        return unitMapper.map(unitRepository.findById(unitId).get());
    }

    @Override
    public Set<UnitDTO> getUnits() {
        return StreamSupport.stream(unitRepository.findAll().spliterator(), true)
                .map(unitMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitDTO addUnit(UnitDTO unitDTO) {
        return unitMapper.map(unitRepository.save(unitMapper.map(unitDTO)));
    }

    @Override
    public UnitDTO updateUnit(UnitDTO unitDTO) {
        return unitMapper.map(unitRepository.save(unitMapper.map(unitDTO)));
    }

    @Override
    public void deleteUnit(UUID unitId) {
        unitRepository.deleteById(unitId);
    }
}
