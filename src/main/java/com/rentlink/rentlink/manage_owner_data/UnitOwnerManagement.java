package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnitOwnerManagement implements UnitOwnerExternalAPI {

    private final UnitOwnerRepository unitOwnerRepository;
    private final UnitOwnerMapper unitOwnerMapper;

    @Override
    public UnitOwnerDTO getUnitOwner(UUID ownerId) {
        return unitOwnerRepository.findById(ownerId).map(unitOwnerMapper::map).get();
    }

    @Override
    public Set<UnitOwnerDTO> getUnitOwners() {
        return StreamSupport.stream(unitOwnerRepository.findAll().spliterator(), false)
                .map(unitOwnerMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitOwnerDTO addUnitOwner(UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerMapper.map(unitOwnerRepository.save(unitOwnerMapper.map(unitOwnerDTO)));
    }

    @Override
    public UnitOwnerDTO updateUnitOwner(UUID id, UnitOwnerDTO unitOwnerDTO) {
        UnitOwner unitOwner = unitOwnerRepository.findById(id).get();
        unitOwnerMapper.update(unitOwnerDTO, unitOwner);
        return unitOwnerMapper.map(unitOwnerRepository.save(unitOwner));
    }

    @Override
    public void deleteUnitOwner(UUID ownerId) {
        unitOwnerRepository.deleteById(ownerId);
    }
}
