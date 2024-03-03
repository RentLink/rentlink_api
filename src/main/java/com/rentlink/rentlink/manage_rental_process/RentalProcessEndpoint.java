package com.rentlink.rentlink.manage_rental_process;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals-process")
@RequiredArgsConstructor
class RentalProcessEndpoint {

    private final RentalProcessExternalAPI processExternalAPI;

    @GetMapping
    List<RentalProcessDTO> getRentalProcessesForOption(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @RequestParam(value = "rentalOptionId") UUID rentalOptionId) {
        return processExternalAPI.getRentalProcessesForOption(rentalOptionId, accountId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    RentalProcessDTO createRentalProcess(
            @RequestHeader(value = X_USER_HEADER) UUID accountId, @RequestBody RentalProcessDTO rentalProcessDTO) {
        return processExternalAPI.createRentalProcess(accountId, rentalProcessDTO);
    }

    @PutMapping("/{rentalProcessId}")
    RentalProcessDTO updateRentalProcess(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @PathVariable UUID rentalProcessId,
            @RequestBody RentalProcessDTO rentalProcessDTO) {
        return processExternalAPI.updateRentalProcess(rentalProcessId, accountId, rentalProcessDTO);
    }

    @PutMapping("/{rentalProcessId}/reject")
    RentalProcessDTO rejectRentalProcess(
            @RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID rentalProcessId) {
        return processExternalAPI.rejectRentalProcess(rentalProcessId, accountId);
    }
}
