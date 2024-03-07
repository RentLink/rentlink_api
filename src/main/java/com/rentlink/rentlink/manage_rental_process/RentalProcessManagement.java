package com.rentlink.rentlink.manage_rental_process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentlink.rentlink.manage_email_comms.EmailOrderInternalAPI;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public RentalProcessDTO createRentalProcess(UUID accountId, RentalProcessDTO rentalProcessDTO) {
        if (processDefinitionManagement.getDefinitions(accountId).stream()
                .noneMatch(processDefinitionDTO -> processDefinitionDTO.equals(rentalProcessDTO.definition()))) {
            throw new RuntimeException("No process def match");
        }
        RentalProcess rentalProcess = rentalProcessMapper
                .map(rentalProcessDTO)
                .nullId()
                .withAccountId(accountId)
                .calculateCurrentStepId()
                .calculateStatus();
        RentalProcess saved = rentalProcessRepository.save(rentalProcess);
        RentalProcessDTO result = rentalProcessMapper.map(saved);
        return result.withPreviousStepId(saved.previousStep());
    }

    @Transactional(readOnly = true)
    @Override
    public List<RentalProcessDTO> getRentalProcessesForOption(UUID uuid, UUID accountId) {
        return rentalProcessRepository
                .findAllByRentalOptionIdAndAccountId(uuid, accountId)
                .map(rentalProcessMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public RentalProcessDTO getRentalProcesses(UUID rentalProcessId, UUID accountId) {
        return rentalProcessRepository
                .findByAccountIdAndId(accountId, rentalProcessId)
                .map(rentalProcess ->
                        rentalProcessMapper.map(rentalProcess).withPreviousStepId(rentalProcess.previousStep()))
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    @Override
    public RentalProcessDTO updateRentalProcess(UUID rentalProcessId, UUID accountId, RentalProcessDTO rentalProcessDTO)
            throws JsonProcessingException {
        if (processDefinitionManagement.getDefinitions(accountId).stream()
                .noneMatch(processDefinitionDTO -> processDefinitionDTO.equals(rentalProcessDTO.definition()))) {
            throw new RuntimeException("No process def match");
        }
        RentalProcess rentalProcess = rentalProcessRepository
                .findByAccountIdAndId(accountId, rentalProcessId)
                .orElseThrow(RuntimeException::new);
        if (rentalProcess.getStatus() == RentalProcessStatus.REJECTED) {
            throw new RuntimeException("Process is rejected");
        }
        if (rentalProcess.getStatus() == RentalProcessStatus.COMPLETED) {
            throw new RuntimeException("Process is finished");
        }
        rentalProcessMapper.update(rentalProcessDTO, rentalProcess);
        rentalProcess.calculateCurrentStepId().calculateStatus();
        rentalProcessRepository.updateDefinition(
                rentalProcess.getId(),
                objectMapper.writeValueAsString(rentalProcess.getDefinition()),
                rentalProcess.getCurrentStepId().toString(),
                rentalProcess.getStatus().toString());
        //        if (saved.currentStep().type().equals(ProcessStepType.SEND_DOCS)) {
        //            var map = currentStep.inputs().stream()
        //                    .collect(Collectors.toMap(ProcessDataInputDTO::label, ProcessDataInputDTO::value));
        //            // TODO: change label names to enums
        //            emailOrderInternalAPI.acceptEmailSendOrder(
        //                    rentalProcess.getAccountId(),
        //                    EmailOrderDTO.orderForSendingDocumentsInRentalProcess(
        //                            (String) map.get("Email"), (List<String>) map.get("Lista dokumentów")));
        //        }
        RentalProcessDTO result = rentalProcessMapper.map(rentalProcess);
        return result.withPreviousStepId(rentalProcess.previousStep());
    }

    @Transactional
    @Override
    public RentalProcessDTO rejectRentalProcess(UUID rentalProcessId, UUID accountId) {
        RentalProcess rentalProcess = rentalProcessRepository
                .findByAccountIdAndId(accountId, rentalProcessId)
                .orElseThrow(RuntimeException::new);
        rentalProcess.setStatus(RentalProcessStatus.REJECTED);
        return rentalProcessMapper.map(rentalProcessRepository.save(rentalProcess));
    }

    @Transactional(readOnly = true)
    @Override
    public List<InternalRentalProcessDTO> findRentalProcessesUpdatedBefore(Instant instant) {
        return rentalProcessRepository.findAllByUpdatedAtBefore(instant).stream()
                .map(internalRentalProcessMapper::map)
                .collect(Collectors.toList());
    }
}
