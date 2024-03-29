package com.rentlink.rentlink.manage_owner_data;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

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
import org.springframework.web.bind.annotation.RequestHeader;
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
    public UnitOwnerDTO getUnitOwner(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID ownerId) {
        return unitOwnerExternalAPI.getUnitOwner(ownerId, accountId);
    }

    @GetMapping(
            value = "",
            params = {"page", "size"})
    Set<UnitOwnerDTO> getUnitOwners(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size) {
        return unitOwnerExternalAPI.getUnitOwners(accountId, page - 1, size);
    }

    @GetMapping(value = "/")
    Set<UnitOwnerDTO> getUnitOwners(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return unitOwnerExternalAPI.getUnitOwners(accountId, null, null);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    UnitOwnerDTO addUnitOwner(
            @RequestHeader(value = X_USER_HEADER) UUID accountId, @RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.addUnitOwner(accountId, unitOwnerDTO);
    }

    @PatchMapping("/{ownerId}")
    UnitOwnerDTO updateUnitOwner(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @PathVariable UUID ownerId,
            @RequestBody UnitOwnerDTO unitOwnerDTO) {
        return unitOwnerExternalAPI.patchUnitOwner(ownerId, accountId, unitOwnerDTO);
    }

    @DeleteMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUnitOwner(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID ownerId) {
        unitOwnerExternalAPI.deleteUnitOwner(ownerId, accountId);
    }
}
