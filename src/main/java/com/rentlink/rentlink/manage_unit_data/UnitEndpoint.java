package com.rentlink.rentlink.manage_unit_data;

import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/unit")
@RequiredArgsConstructor
class UnitEndpoint {

    private final UnitExternalAPI unitExternalAPI;

    @GetMapping("/{unitId}")
    public UnitDTO getUnit(@PathVariable UUID unitId) {
        return unitExternalAPI.getUnit(unitId);
    }

    @GetMapping("/")
    Set<UnitDTO> getUnits() {
        return unitExternalAPI.getUnits(null, null);
    }

    @GetMapping(
            value = "",
            params = {"page", "size"})
    Set<UnitDTO> getUnits(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        return unitExternalAPI.getUnits(page - 1, size);
    }

    @PostMapping("/")
    UnitDTO addUnit(@RequestBody UnitDTO unitDTO) {
        return unitExternalAPI.addUnit(unitDTO);
    }

    @PatchMapping("/{unitId}")
    UnitDTO updateUnit(@PathVariable UUID unitId, @RequestBody UnitDTO unitDTO) {
        return unitExternalAPI.updateUnit(unitId, unitDTO);
    }

    @DeleteMapping("/{unitId}")
    void deleteUnit(@PathVariable UUID unitId) {
        unitExternalAPI.deleteUnit(unitId);
    }
}
