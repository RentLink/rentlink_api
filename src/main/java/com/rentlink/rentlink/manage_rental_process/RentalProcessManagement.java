package com.rentlink.rentlink.manage_rental_process;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RentalProcessManagement implements RentalProcessExternalAPI, RentalProcessInternalAPI {

    private final RentalProcessRepository rentalProcessRepository;

    private final RentalProcessMapper rentalProcessMapper;

    private final ProcessDefinitionManagement processDefinitionManagement;

    @Override
    public RentalProcessDTO createRentalProcess(RentalProcessDTO rentalProcessDTO) {
        if (processDefinitionManagement.getDefinitions().stream()
                .noneMatch(processDefinitionDTO -> processDefinitionDTO.equals(rentalProcessDTO.definition()))) {
            throw new RuntimeException("No process def match");
        }
        RentalProcess rentalProcess = rentalProcessMapper
                .map(rentalProcessDTO)
                .nullId()
                .calculateCurrentStepId()
                .calculateStatus();

        return rentalProcessMapper.map(rentalProcessRepository.save(rentalProcess));
    }

    @Override
    public List<RentalProcessDTO> getRentalProcessesForOption(UUID uuid) {
        return rentalProcessRepository.findAllByRentalOptionId(uuid).stream()
                .map(rentalProcessMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public RentalProcessDTO updateRentalProcess(UUID rentalProcessId, RentalProcessDTO rentalProcessDTO) {
        if (processDefinitionManagement.getDefinitions().stream()
                .noneMatch(processDefinitionDTO -> processDefinitionDTO.equals(rentalProcessDTO.definition()))) {
            throw new RuntimeException("No process def match");
        }
        RentalProcess rentalProcess =
                rentalProcessRepository.findById(rentalProcessId).orElseThrow(RuntimeException::new);
        if (rentalProcess.getStatus() == RentalProcessStatus.REJECTED) {
            throw new RuntimeException("Process is rejected");
        }
        if (rentalProcess.getStatus() == RentalProcessStatus.COMPLETED) {
            throw new RuntimeException("Process is finished");
        }
        rentalProcessMapper.update(rentalProcessDTO, rentalProcess);
        rentalProcess.calculateCurrentStepId().calculateStatus();
        return rentalProcessMapper.map(rentalProcessRepository.save(rentalProcess));
    }

    @Override
    public RentalProcessDTO rejectRentalProcess(UUID rentalProcessId) {
        RentalProcess rentalProcess =
                rentalProcessRepository.findById(rentalProcessId).orElseThrow(RuntimeException::new);
        rentalProcess.setStatus(RentalProcessStatus.REJECTED);
        return rentalProcessMapper.map(rentalProcessRepository.save(rentalProcess));
    }

    @Override
    public List<RentalProcessDTO> findRentalProcessesUpdatedBefore(Instant instant) {
        return rentalProcessRepository.findAllByUpdatedAtBefore(instant).stream()
                .map(rentalProcessMapper::map)
                .collect(Collectors.toList());
    }
}
