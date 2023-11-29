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
    public UnitDTO getTenant(@PathVariable UUID unitId) {
        return unitExternalAPI.getUnit(unitId);
    }

    @GetMapping("/")
    Set<UnitDTO> getTenants() {
        return unitExternalAPI.getUnits(null, null);
    }

    @GetMapping(
            value = "",
            params = {"page", "size"})
    Set<UnitDTO> getTenants(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        return unitExternalAPI.getUnits(page - 1, size);
    }

    @PostMapping("/")
    UnitDTO addTenant(@RequestBody UnitDTO unitDTO) {
        return unitExternalAPI.addUnit(unitDTO);
    }

    @PutMapping("/{unitId}")
    UnitDTO updateTenant(@RequestBody UnitDTO unitDTO) {
        return unitExternalAPI.updateUnit(unitDTO);
    }

    @DeleteMapping("/{unitId}")
    void deleteTenant(@PathVariable UUID unitId) {
        unitExternalAPI.deleteUnit(unitId);
    }
}
