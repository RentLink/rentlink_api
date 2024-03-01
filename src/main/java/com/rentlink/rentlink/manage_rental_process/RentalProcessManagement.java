package com.rentlink.rentlink.manage_rental_process;

import com.rentlink.rentlink.manage_email_comms.EmailOrderDTO;
import com.rentlink.rentlink.manage_email_comms.EmailOrderInternalAPI;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// TODO: Exception handling
// TODO: Throws RuntimeException
@Service
@RequiredArgsConstructor
class RentalProcessManagement implements RentalProcessExternalAPI, RentalProcessInternalAPI {

    private final RentalProcessRepository rentalProcessRepository;

    private final RentalProcessMapper rentalProcessMapper;

    private final InternalRentalProcessMapper internalRentalProcessMapper;

    private final ProcessDefinitionManagement processDefinitionManagement;

    private final EmailOrderInternalAPI emailOrderInternalAPI;

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

        RentalProcess saved = rentalProcessRepository.save(rentalProcess);
        RentalProcessDTO result = rentalProcessMapper.map(saved);
        return result.withPreviousStepId(saved.previousStep());
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
        RentalProcess saved = rentalProcessRepository.save(rentalProcess);
        ProcessStepDTO currentStep = saved.currentStep();
        if (currentStep.type().equals(ProcessStepType.SEND_DOCS)) {
            var map = currentStep.inputs().stream()
                    .collect(Collectors.toMap(ProcessDataInputDTO::label, ProcessDataInputDTO::value));
            // TODO: change label names to enums
            emailOrderInternalAPI.acceptEmailSendOrder(EmailOrderDTO.orderForSendingDocumentsInRentalProcess(
                    (String) map.get("Email"), (List<String>) map.get("Lista dokumentów")));
        }
        RentalProcessDTO result = rentalProcessMapper.map(saved);
        return result.withPreviousStepId(saved.previousStep());
    }

    @Override
    public RentalProcessDTO rejectRentalProcess(UUID rentalProcessId) {
        RentalProcess rentalProcess =
                rentalProcessRepository.findById(rentalProcessId).orElseThrow(RuntimeException::new);
        rentalProcess.setStatus(RentalProcessStatus.REJECTED);
        return rentalProcessMapper.map(rentalProcessRepository.save(rentalProcess));
    }

    @Override
    public List<InternalRentalProcessDTO> findRentalProcessesUpdatedBefore(Instant instant) {
        return rentalProcessRepository.findAllByUpdatedAtBefore(instant).stream()
                .map(internalRentalProcessMapper::map)
                .collect(Collectors.toList());
    }
}
