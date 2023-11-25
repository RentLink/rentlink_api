package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    UnitOwnerDTO addUnitOwner(@RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.addUnitOwner(unitOwnerDTO);
    }

    @PatchMapping("/{ownerId}")
    UnitOwnerDTO updateUnitOwner(@PathVariable UUID ownerId, @RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.updateUnitOwner(ownerId, unitOwnerDTO);
    }

    @DeleteMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUnitOwner(@PathVariable UUID ownerId) {
        unitOwnerExternalAPI.deleteUnitOwner(ownerId);
    }
}
