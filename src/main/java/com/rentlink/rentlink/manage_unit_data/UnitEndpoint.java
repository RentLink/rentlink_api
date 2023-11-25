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
        return unitExternalAPI.getUnits();
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
