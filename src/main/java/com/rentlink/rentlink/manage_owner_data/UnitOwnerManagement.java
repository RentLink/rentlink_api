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
        return unitOwnerRepository.findById(ownerId).map(unitOwnerMapper::toDTO).get();
    }

    @Override
    public Set<UnitOwnerDTO> getUnitOwners() {
        return StreamSupport.stream(unitOwnerRepository.findAll().spliterator(), false)
                .map(unitOwnerMapper::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitOwnerDTO addUnitOwner(UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerMapper.toDTO(unitOwnerRepository.save(unitOwnerMapper.toDB(unitOwnerDTO)));
    }

    @Override
    public UnitOwnerDTO updateUnitOwner(UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerMapper.toDTO(unitOwnerRepository.save(unitOwnerMapper.toDB(unitOwnerDTO)));
    }

    @Override
    public void deleteUnitOwner(UUID ownerId) {
        unitOwnerRepository.deleteById(ownerId);
    }
}
