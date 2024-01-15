package com.rentlink.rentlink.manage_unit_data;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UnitManagement implements UnitExternalAPI {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    public UnitDTO getUnit(UUID unitId) {
        return unitMapper.map(unitRepository.findById(unitId).orElseThrow(UnitNotFoundException::new));
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

        return stream.map(unitMapper::map).collect(Collectors.toSet());
    }

    @Override
    public UnitDTO addUnit(UnitDTO unitDTO) {
        return unitMapper.map(unitRepository.save(unitMapper.map(unitDTO)));
    }

    @Override
    public UnitDTO patchTenant(UUID id, UnitDTO unitDTO) {
        Unit unit = unitRepository.findById(id).orElseThrow(UnitNotFoundException::new);
        unitMapper.update(unitDTO, unit);
        return unitMapper.map(unitRepository.save(unit));
    }

    @Override
    public void deleteUnit(UUID unitId) {
        unitRepository.deleteById(unitId);
    }
}
