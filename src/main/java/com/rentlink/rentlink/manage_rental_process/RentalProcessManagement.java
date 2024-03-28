package com.rentlink.rentlink.manage_rental_process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentlink.rentlink.common.DocumentDTO;
import com.rentlink.rentlink.manage_email_inbound.AwaitingDocumentsInternalAPI;
import com.rentlink.rentlink.manage_email_outbound.EmailOrderInternalAPI;
import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import com.rentlink.rentlink.manage_incoming_events.IncomingEventInternalAPI;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessDefinitionDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessDefinitionExternalAPI;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepType;
import com.rentlink.rentlink.manage_rental_process.side_effects.MeetingSideEffect;
import com.rentlink.rentlink.manage_rental_process.side_effects.NoOpSideEffect;
import com.rentlink.rentlink.manage_rental_process.side_effects.RentalProcessStepSideEffect;
import com.rentlink.rentlink.manage_rental_process.side_effects.RentalProcessStepSideEffectContext;
import com.rentlink.rentlink.manage_rental_process.side_effects.SendDocumentsSideEffect;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: Exception handling
// TODO: Throws RuntimeException
@Service
@Slf4j
class RentalProcessManagement implements RentalProcessExternalAPI, RentalProcessInternalAPI {

    public static final NoOpSideEffect NO_OP_SIDE_EFFECT = new NoOpSideEffect();
    private final RentalProcessRepository rentalProcessRepository;

    private final RentalProcessMapper rentalProcessMapper;

    private final InternalRentalProcessMapper internalRentalProcessMapper;

    private final ProcessDefinitionExternalAPI processDefinitionManagement;

    private final EmailOrderInternalAPI emailOrderInternalAPI;

    private final AwaitingDocumentsInternalAPI awaitingDocumentsInternalAPI;

    private final FilesManagerInternalAPI filesManagerInternalAPI;

    private final ObjectMapper objectMapper;

    private final Map<ProcessStepType, RentalProcessStepSideEffect> sideEffects;

    public RentalProcessManagement(
            RentalProcessRepository rentalProcessRepository,
            RentalProcessMapper rentalProcessMapper,
            InternalRentalProcessMapper internalRentalProcessMapper,
            ProcessDefinitionExternalAPI processDefinitionManagement,
            EmailOrderInternalAPI emailOrderInternalAPI,
            AwaitingDocumentsInternalAPI awaitingDocumentsInternalAPI,
            FilesManagerInternalAPI filesManagerInternalAPI,
            IncomingEventInternalAPI incomingEventInternalAPI,
            ObjectMapper objectMapper) {
        this.rentalProcessRepository = rentalProcessRepository;
        this.rentalProcessMapper = rentalProcessMapper;
        this.internalRentalProcessMapper = internalRentalProcessMapper;
        this.processDefinitionManagement = processDefinitionManagement;
        this.emailOrderInternalAPI = emailOrderInternalAPI;
        this.awaitingDocumentsInternalAPI = awaitingDocumentsInternalAPI;
        this.filesManagerInternalAPI = filesManagerInternalAPI;
        this.objectMapper = objectMapper;
        this.sideEffects = Map.of(
                ProcessStepType.MEETING, new MeetingSideEffect(incomingEventInternalAPI),
                ProcessStepType.SEND_DOCS,
                        new SendDocumentsSideEffect(awaitingDocumentsInternalAPI, emailOrderInternalAPI));
    }

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
                .calculateCurrentStepType()
                .calculateStatus();
        RentalProcess saved = rentalProcessRepository.save(rentalProcess);

        calculateStepsRange(null, rentalProcessDTO).forEach(step -> sideEffects
                .getOrDefault(step.type(), NO_OP_SIDE_EFFECT)
                .execute(new RentalProcessStepSideEffectContext(
                        accountId,
                        saved.getId(),
                        rentalProcessDTO.definition().steps(),
                        step.type() == ProcessStepType.SEND_DOCS ? UUID.randomUUID() : null)));

        RentalProcessDTO result = rentalProcessMapper.map(saved);
        return result.withPreviousStepId(saved.lastFilledStep().stepId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<RentalProcessDTO> getRentalProcessesForOption(UUID uuid, UUID accountId) {
        return rentalProcessRepository
                .findAllByRentalOptionIdAndAccountId(uuid, accountId)
                .map(rentalProcess -> rentalProcessMapper
                        .map(rentalProcess)
                        .withPreviousStepId(rentalProcess.lastFilledStep().stepId()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public RentalProcessDTO getRentalProcesses(UUID rentalProcessId, UUID accountId) {
        return rentalProcessRepository
                .findByAccountIdAndId(accountId, rentalProcessId)
                .map(rentalProcess -> rentalProcessMapper
                        .map(rentalProcess)
                        .withPreviousStepId(rentalProcess.lastFilledStep().stepId()))
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
        if (rentalProcess.isProcessRejected()) {
            throw new RuntimeException("Process is rejected");
        }
        if (rentalProcess.isProcessCompleted()) {
            throw new RuntimeException("Process is finished");
        }
        List<ProcessStepDTO> stepToExecuteSideEffects = calculateStepsRange(rentalProcess, rentalProcessDTO);

        rentalProcessMapper.update(rentalProcessDTO, rentalProcess);
        rentalProcess.calculateCurrentStepId().calculateCurrentStepType().calculateStatus();
        rentalProcessRepository.updateDefinition(
                rentalProcess.getId(),
                objectMapper.writeValueAsString(rentalProcess.getDefinition()),
                rentalProcess.getCurrentStepId().toString(),
                rentalProcess.getStatus().toString());
        ProcessStepDTO lastFilledStep = rentalProcess.lastFilledStep();

        stepToExecuteSideEffects.forEach(step -> sideEffects
                .getOrDefault(step.type(), NO_OP_SIDE_EFFECT)
                .execute(new RentalProcessStepSideEffectContext(
                        accountId,
                        rentalProcess.getId(),
                        rentalProcessDTO.definition().steps(),
                        step.type() == ProcessStepType.SEND_DOCS ? UUID.randomUUID() : null)));

        RentalProcessDTO result = rentalProcessMapper.map(rentalProcess);
        return result.withPreviousStepId(lastFilledStep.stepId());
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

    @Override
    public AwaitingRentalProcessDocsDTO getAwaitingRentalProcessDocs(UUID rentalProcessId, UUID accountId) {
        List<DocumentDTO> files = filesManagerInternalAPI
                .getFileNames("%s/rental-process/%s".formatted(accountId.toString(), rentalProcessId.toString()))
                .stream()
                .map(DocumentDTO::fromFileMetadata)
                .toList();
        return new AwaitingRentalProcessDocsDTO(files);
    }

    @Transactional
    @Override
    public void advanceProcessOnIncomingDocuments(UUID rentalProcessId, UUID accountId) {
        rentalProcessRepository.findByAccountIdAndId(accountId, rentalProcessId).ifPresent(rentalProcess -> {
            ProcessStepDTO awaitingDocsStep = rentalProcess.getDefinition().steps().stream()
                    .filter(step -> step.type().equals(ProcessStepType.AWAITING_DOCUMENTS))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
            ProcessStepDTO nextStep = rentalProcess.getDefinition().steps().stream()
                    .filter(step -> step.order() == awaitingDocsStep.order() + 1)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
            rentalProcess.setCurrentStepId(nextStep.stepId());
            rentalProcess.setCurrentStepType(nextStep.type());
            rentalProcessRepository.save(rentalProcess);
        });
    }

    private List<ProcessStepDTO> calculateStepsRange(RentalProcess rentalProcess, RentalProcessDTO rentalProcessDTO) {
        if (rentalProcess == null && rentalProcessDTO != null) {
            // CREATE FLOW
            return extractSteps(rentalProcessDTO.definition());

        } else if (rentalProcess != null && rentalProcessDTO != null) {
            List<ProcessStepDTO> receivedStep = extractSteps(rentalProcessDTO.definition());

            Set<ProcessStepType> savedSteps = extractSteps(rentalProcess.getDefinition()).stream()
                    .map(ProcessStepDTO::type)
                    .collect(Collectors.toSet());

            // UPDATE FLOW
            return receivedStep.stream()
                    .filter(processStepDTO -> !savedSteps.contains(processStepDTO.type()))
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    private static List<ProcessStepDTO> extractSteps(ProcessDefinitionDTO processDefinition) {
        return processDefinition.steps().stream()
                .filter(processStepDTO -> processStepDTO.inputs() != null
                        && !processStepDTO.inputs().isEmpty())
                .filter(processStepDTO -> processStepDTO.inputs().stream()
                        .filter(processDataInputDTO -> !processDataInputDTO.isOptional())
                        .allMatch(processDataInputDTO -> processDataInputDTO.value() != null))
                .sorted(Comparator.comparing(ProcessStepDTO::order))
                .toList();
    }
}
