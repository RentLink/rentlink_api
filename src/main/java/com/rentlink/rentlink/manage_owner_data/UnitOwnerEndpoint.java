package com.rentlink.rentlink.manage_owner_data;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
class UnitOwnerEndpoint {

    private final UnitOwnerExternalAPI unitOwnerExternalAPI;

    @GetMapping("/{ownerId}")
    public UnitOwnerDTO getUnitOwner(@PathVariable UUID ownerId) {
        return unitOwnerExternalAPI.getUnitOwner(ownerId);
    }

    @GetMapping("/")
    Set<UnitOwnerDTO> getUnitOwners() {
        return unitOwnerExternalAPI.getUnitOwners();
    }

    @PostMapping("/")
    UnitOwnerDTO addUnitOwner(@RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.addUnitOwner(unitOwnerDTO);
    }

    @PutMapping("/{ownerId}")
    UnitOwnerDTO updateUnitOwner(@RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.updateUnitOwner(unitOwnerDTO);
    }

    @DeleteMapping("/{ownerId}")
    void deleteUnitOwner(@PathVariable UUID ownerId) {
        unitOwnerExternalAPI.deleteUnitOwner(ownerId);
    }
}
