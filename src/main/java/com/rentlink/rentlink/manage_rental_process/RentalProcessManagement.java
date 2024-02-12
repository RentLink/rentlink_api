package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RentalProcessManagement implements RentalProcessExternalAPI {

    private final RentalProcessRepository rentalProcessRepository;

    private final RentalProcessMapper rentalProcessMapper;

    @Override
    public RentalProcessDTO createRentalProcess(RentalProcessDTO rentalProcessDTO) {
        RentalProcess rentalProcess = rentalProcessMapper.map(rentalProcessDTO);
        return rentalProcessMapper.map(rentalProcessRepository.save(rentalProcess));
    }

    public List<RentalProcessDTO> getRentalProcessesForOption(UUID uuid) {
        return rentalProcessRepository.findAllByRentalOptionId(uuid).stream()
                .map(rentalProcessMapper::map)
                .collect(Collectors.toList());
    }
}
