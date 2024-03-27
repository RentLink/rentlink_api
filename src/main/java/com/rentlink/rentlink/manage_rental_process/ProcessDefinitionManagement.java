package com.rentlink.rentlink.manage_rental_process;

import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProcessDefinitionManagement implements ProcessDefinitionExternalAPI {

    private final FilesManagerInternalAPI filesManagerInternalAPI;

    @Override
    public List<ProcessDefinitionDTO> getDefinitions(UUID accountId) {
        return emptyDefinitions(accountId);
    }

    private List<ProcessDefinitionDTO> emptyDefinitions(UUID accountId) {
        return List.of(
                new ProcessDefinitionDTO(
                        UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf"),
                        "Pełny proces",
                        ProcessDefinitionType.SYSTEM,
                        List.of(
                                createInitialStep(),
                                createMeetStep(),
                                createSendDocsStep(accountId),
                                createAwaitingDocsStep(),
                                createVerificationStep())),
                new ProcessDefinitionDTO(
                        UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabe"),
                        "Skrócony proces",
                        ProcessDefinitionType.SYSTEM,
                        List.of(createInitialStep(), createMeetStep())));
    }

    private ProcessStepDTO createInitialStep() {
        ProcessDataInputDTO<String> nameStep =
                ProcessDataInputDTO.createLiteralProcessEntryValue("Nazwa", ProcessDataInputIdentifier.NAME, false, 1);
        ProcessDataInputDTO<String> phoneStep = ProcessDataInputDTO.createLiteralProcessEntryValue(
                "Telefon", ProcessDataInputIdentifier.PHONE, false, 2);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf"),
                "Wstępny kontakt",
                1,
                ProcessStepType.INITIAL_COMMUNICATION,
                List.of(nameStep, phoneStep));
    }

    private ProcessStepDTO createMeetStep() {
        ProcessDataInputDTO<LocalDate> name = ProcessDataInputDTO.createDateProcessEntryValue(
                "Data spotkania", ProcessDataInputIdentifier.MEETING_DATE, false, 1, null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab2"),
                "Pokazanie mieszkania",
                2,
                ProcessStepType.MEETING,
                List.of(name));
    }

    private ProcessStepDTO createSendDocsStep(UUID accountId) {
        ProcessDataInputDTO<String> email = ProcessDataInputDTO.createLiteralProcessEntryValue(
                "E-mail", ProcessDataInputIdentifier.EMAIL, false, 1);
        ProcessDataInputDTO<List<String>> fileList = ProcessDataInputDTO.createMultiSelectValue(
                "Lista dokumentów",
                ProcessDataInputIdentifier.DOC_LIST,
                false,
                2,
                filesManagerInternalAPI.getFileNames(accountId.toString()).stream()
                        .map(fileDTO -> new ProcessDataInputSelectValueDTO(fileDTO.name()))
                        .collect(Collectors.toSet()),
                null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab4"),
                "Przesłanie dokumentów",
                3,
                ProcessStepType.SEND_DOCS,
                List.of(email, fileList));
    }

    private ProcessStepDTO createAwaitingDocsStep() {
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab1"),
                "Oczekiwanie na dokumenty",
                4,
                ProcessStepType.AWAITING_DOCUMENTS,
                Collections.emptyList());
    }

    private ProcessStepDTO createVerificationStep() {
        ProcessDataInputDTO<String> identityVerification = ProcessDataInputDTO.createSelectValue(
                "Weryfikacja tożsamości",
                ProcessDataInputIdentifier.IDENTITY_VERIFICATION,
                false,
                1,
                Set.of(
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.POSITIVE.name()),
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.NEGATIVE.name())),
                null);
        ProcessDataInputDTO<String> incomeVerification = ProcessDataInputDTO.createSelectValue(
                "Weryfikacja dochodu",
                ProcessDataInputIdentifier.INCOME_VERIFICATION,
                false,
                2,
                Set.of(
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.POSITIVE.name()),
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.NEGATIVE.name())),
                null);
        ProcessDataInputDTO<String> surveyVerification = ProcessDataInputDTO.createSelectValue(
                "Weryfikacja ankiety",
                ProcessDataInputIdentifier.SURVEY_VERIFICATION,
                false,
                3,
                Set.of(
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.POSITIVE.name()),
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.NEGATIVE.name())),
                null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab3"),
                "Weryfikacja najemcy",
                5,
                ProcessStepType.VALIDATION,
                List.of(identityVerification, incomeVerification, surveyVerification));
    }
}
