package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals-process")
@RequiredArgsConstructor
public class RentalProcessEndpoint {

    private final ProcessDefinitionManagement processDefinitionManagement;

    @GetMapping("/")
    List<ProcessDefinition> getRentalProcesses() {
        return null;
    }

    @GetMapping
    List<ProcessDefinition> getRentalProcessesForOption(@RequestParam(value = "rentalOptionId") UUID rentalOptionId) {
        return null;
    }

    @PostMapping("/")
    void createRentalProcess() {}
}
