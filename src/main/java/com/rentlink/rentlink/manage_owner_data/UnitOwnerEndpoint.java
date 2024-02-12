package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
class UnitOwnerEndpoint {

    private final UnitOwnerExternalAPI unitOwnerExternalAPI;

    @GetMapping("/{ownerId}")
    public UnitOwnerDTO getUnitOwner(@PathVariable UUID ownerId) {
        return unitOwnerExternalAPI.getUnitOwner(ownerId);
    }

    @GetMapping(
            value = "",
            params = {"page", "size"})
    Set<UnitOwnerDTO> getUnitOwners(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size) {
        return unitOwnerExternalAPI.getUnitOwners(page - 1, size);
    }

    @GetMapping(value = "/")
    Set<UnitOwnerDTO> getUnitOwners() {
        return unitOwnerExternalAPI.getUnitOwners(null, null);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    UnitOwnerDTO addUnitOwner(@RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.addUnitOwner(unitOwnerDTO);
    }

    @PatchMapping("/{ownerId}")
    UnitOwnerDTO updateUnitOwner(@PathVariable UUID ownerId, @RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.patchUnitOwner(ownerId, unitOwnerDTO);
    }

    @DeleteMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUnitOwner(@PathVariable UUID ownerId) {
        unitOwnerExternalAPI.deleteUnitOwner(ownerId);
    }
}
