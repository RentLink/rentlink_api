package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals-process")
@RequiredArgsConstructor
class RentalProcessEndpoint {

    private final RentalProcessManagement rentalProcessManagement;

    @GetMapping
    List<RentalProcessDTO> getRentalProcessesForOption(@RequestParam(value = "rentalOptionId") UUID rentalOptionId) {
        return rentalProcessManagement.getRentalProcessesForOption(rentalOptionId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    RentalProcessDTO createRentalProcess(@RequestBody RentalProcessDTO rentalProcessDTO) {
        return rentalProcessManagement.createRentalProcess(rentalProcessDTO);
    }
}
