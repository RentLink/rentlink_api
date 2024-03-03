package com.rentlink.rentlink.manage_unit_data;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/unit")
@RequiredArgsConstructor
class UnitEndpoint {

    private final UnitExternalAPI unitExternalAPI;

    @GetMapping("/{unitId}")
    public UnitDTO getUnit(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID unitId) {
        return unitExternalAPI.getUnit(unitId, accountId);
    }

    @GetMapping("/")
    Set<UnitDTO> getUnits(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return unitExternalAPI.getUnits(accountId, null, null);
    }

    @GetMapping(
            value = "",
            params = {"page", "size"})
    Set<UnitDTO> getUnits(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        return unitExternalAPI.getUnits(accountId, page - 1, size);
    }

    @PostMapping("/")
    UnitDTO addUnit(@RequestHeader(value = X_USER_HEADER) UUID accountId, @RequestBody UnitDTO unitDTO) {
        return unitExternalAPI.addUnit(unitDTO, accountId);
    }

    @PatchMapping("/{unitId}")
    UnitDTO updateUnit(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @PathVariable UUID unitId,
            @RequestBody UnitDTO unitDTO) {
        return unitExternalAPI.updateUnit(unitId, accountId, unitDTO);
    }

    @PutMapping(
            value = "/{unitId}/upload-images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @PathVariable UUID unitId,
            @RequestPart(value = "files") MultipartFile[] files,
            @RequestPart(value = "rentalOptionId", required = false) UUID rentalOptionId) {
        unitExternalAPI.uploadImages(unitId, accountId, rentalOptionId, Set.of(files));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping(
            value = "/{unitId}/rental-option/{rentalOptionId}/upload-images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @PathVariable UUID unitId,
            @PathVariable UUID rentalOptionId,
            @RequestPart(value = "files") MultipartFile[] files) {
        unitExternalAPI.uploadImages(unitId, accountId, rentalOptionId, Set.of(files));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/{unitId}")
    void deleteUnit(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID unitId) {
        unitExternalAPI.deleteUnit(unitId, accountId);
    }
}
