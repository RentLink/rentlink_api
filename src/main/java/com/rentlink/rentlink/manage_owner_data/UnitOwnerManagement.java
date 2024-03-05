package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UnitOwnerManagement implements UnitOwnerExternalAPI {

    private final UnitOwnerRepository unitOwnerRepository;
    private final UnitOwnerMapper unitOwnerMapper;

    @Transactional(readOnly = true)

    @Override
    public UnitOwnerDTO getUnitOwner(UUID ownerId, UUID accountId) {
        return unitOwnerRepository
                .findByAccountIdAndId(accountId, ownerId)
                .map(unitOwnerMapper::map)
                .orElseThrow(UnitOwnerNotFoundException::new);
    }

    @Transactional(readOnly = true)

    @Override
    public Set<UnitOwnerDTO> getUnitOwners(UUID accountId, Integer page, Integer pageSize) {
        Stream<UnitOwner> stream;
        if (page != null && pageSize != null) {
            Pageable pageable = PageRequest.of(page, pageSize);
            stream = unitOwnerRepository.findAllByAccountId(accountId, pageable);
        } else {
            stream = unitOwnerRepository.findAllByAccountId(accountId);
        }

        return stream.map(unitOwnerMapper::map).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public UnitOwnerDTO addUnitOwner(UUID accountId, UnitOwnerDTO unitOwnerDTO) {
        UnitOwner unitOwner = unitOwnerMapper.map(unitOwnerDTO);
        unitOwner.setAccountId(accountId);
        return unitOwnerMapper.map(unitOwnerRepository.save(unitOwner));
    }

    @Transactional
    @Override
    public UnitOwnerDTO patchUnitOwner(UUID id, UUID accountId, UnitOwnerDTO unitOwnerDTO) {
        UnitOwner unitOwner =
                unitOwnerRepository.findByAccountIdAndId(accountId, id).orElseThrow(UnitOwnerNotFoundException::new);
        unitOwnerMapper.update(unitOwnerDTO, unitOwner);
        return unitOwnerMapper.map(unitOwnerRepository.save(unitOwner));
    }

    @Transactional
    @Override
    public void deleteUnitOwner(UUID ownerId, UUID accountId) {
        unitOwnerRepository.deleteByAccountIdAndId(accountId, ownerId);
    }
}
