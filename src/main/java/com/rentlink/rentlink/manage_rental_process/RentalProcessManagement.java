package com.rentlink.rentlink.manage_rental_process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class RentalProcessManagement implements RentalProcessExternalAPI {

    private final RentalProcessRepository rentalProcessRepository;

    private final RentalProcessMapper rentalProcessMapper;

    private final ProcessDefinitionManagement processDefinitionManagement;

    @Override
    public RentalProcessDTO createRentalProcess(RentalProcessDTO rentalProcessDTO) {
        if(processDefinitionManagement.getDefinitions().stream().noneMatch(processDefinitionDTO -> processDefinitionDTO.equals(rentalProcessDTO.definition()))) {
            throw new RuntimeException("No process def match");
        }
        RentalProcess rentalProcess = rentalProcessMapper.map(rentalProcessDTO);
        rentalProcess.nullId();
        rentalProcess.calculateCurrentStepId();

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
        if(processDefinitionManagement.getDefinitions().stream().noneMatch(processDefinitionDTO -> processDefinitionDTO.equals(rentalProcessDTO.definition()))) {
            throw new RuntimeException("No process def match");
        }
        RentalProcess rentalProcess =
                rentalProcessRepository.findById(rentalProcessId).orElseThrow(RuntimeException::new);
        rentalProcessMapper.update(rentalProcessDTO, rentalProcess);
        rentalProcess.calculateCurrentStepId();
        return rentalProcessMapper.map(rentalProcessRepository.save(rentalProcess));
    }
}
