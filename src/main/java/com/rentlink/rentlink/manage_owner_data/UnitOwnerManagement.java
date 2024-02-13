package com.rentlink.rentlink.manage_owner_data;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
class UnitOwnerManagement implements UnitOwnerExternalAPI {

    private final UnitOwnerRepository unitOwnerRepository;
    private final UnitOwnerMapper unitOwnerMapper;

    @Override
    public UnitOwnerDTO getUnitOwner(UUID ownerId) {
        return unitOwnerRepository
                .findById(ownerId)
                .map(unitOwnerMapper::map)
                .orElseThrow(UnitOwnerNotFoundException::new);
    }

    @Override
    public Set<UnitOwnerDTO> getUnitOwners(Integer page, Integer pageSize) {
        Stream<UnitOwner> stream;
        if (page != null && pageSize != null) {
            Pageable pageable = PageRequest.of(page, pageSize);
            stream = StreamSupport.stream(unitOwnerRepository.findAll(pageable).spliterator(), false);
        } else {
            stream = unitOwnerRepository.findAll().stream();
        }

        return stream.map(unitOwnerMapper::map).collect(Collectors.toSet());
    }

    @Override
    public UnitOwnerDTO addUnitOwner(UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerMapper.map(unitOwnerRepository.save(unitOwnerMapper.map(unitOwnerDTO)));
    }

    @Override
    public UnitOwnerDTO patchUnitOwner(UUID id, UnitOwnerDTO unitOwnerDTO) {
        UnitOwner unitOwner = unitOwnerRepository.findById(id).orElseThrow(UnitOwnerNotFoundException::new);
        unitOwnerMapper.update(unitOwnerDTO, unitOwner);
        return unitOwnerMapper.map(unitOwnerRepository.save(unitOwner));
    }

    @Override
    public void deleteUnitOwner(UUID ownerId) {
        unitOwnerRepository.deleteById(ownerId);
    }
}
