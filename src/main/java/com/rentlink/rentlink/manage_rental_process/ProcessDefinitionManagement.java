package com.rentlink.rentlink.manage_rental_process;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
class ProcessDefinitionManagement implements ProcessDefinitionExternalAPI {
    @Override
    public List<ProcessDefinitionDTO> getDefinitions() {
        return emptyDefinitions();
    }

    private List<ProcessDefinitionDTO> emptyDefinitions() {
        return List.of(
                new ProcessDefinitionDTO(
                        UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf"),
                        "Pełny proces",
                        ProcessDefinitionType.SYSTEM,
                        List.of(
                                createInitialStep(null, null, null),
                                createMeetStep(),
                                createAwaitingDocsStep(),
                                createVerificationStep(),
                                endProcess())),
                new ProcessDefinitionDTO(
                        UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabe"),
                        "Skrócony proces",
                        ProcessDefinitionType.SYSTEM,
                        List.of(createInitialStep(null, null, null), createMeetStep(), endProcess())));
    }

    private ProcessStepDTO createInitialStep(String name, String phone, String email) {
        ProcessDataInputDTO<String> nameStep =
                ProcessDataInputDTO.createLiteralProcessEntryValue("Nazwa", false, 1, name);
        ProcessDataInputDTO<String> phoneStep =
                ProcessDataInputDTO.createLiteralProcessEntryValue("Telefon", false, 2, phone);
        ProcessDataInputDTO<String> emailStep =
                ProcessDataInputDTO.createLiteralProcessEntryValue("E-mail", true, 3, email);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf"),
                "Wstępny kontakt",
                1,
                ProcessStepType.STEP,
                List.of(nameStep, phoneStep, emailStep));
    }

    private ProcessStepDTO createMeetStep() {
        ProcessDataInputDTO<LocalDate> name =
                ProcessDataInputDTO.createDateProcessEntryValue("Data spotkania", false, 1, null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab2"),
                "Pokazanie mieszkania",
                2,
                ProcessStepType.STEP,
                List.of(name));
    }

    private ProcessStepDTO createAwaitingDocsStep() {
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab1"),
                "Oczekiwanie na dokumenty",
                3,
                ProcessStepType.STEP,
                Collections.emptyList());
    }

    private ProcessStepDTO createVerificationStep() {
        ProcessDataInputDTO<String> identityVerification = ProcessDataInputDTO.createEnumeratedProcessEntryValue(
                "Weryfikacja tożsamości",
                false,
                1,
                Set.of(
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.POSITIVE.name()),
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.NEGATIVE.name())),
                null);
        ProcessDataInputDTO<String> incomeVerification = ProcessDataInputDTO.createEnumeratedProcessEntryValue(
                "Weryfikacja dochodu",
                false,
                2,
                Set.of(
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.POSITIVE.name()),
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.NEGATIVE.name())),
                null);
        ProcessDataInputDTO<String> surveyVerification = ProcessDataInputDTO.createEnumeratedProcessEntryValue(
                "Weryfikacja ankiety",
                false,
                3,
                Set.of(
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.POSITIVE.name()),
                        new ProcessDataInputSelectValueDTO(VerificationOutcome.NEGATIVE.name())),
                null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab3"),
                "Weryfikacja najemcy",
                4,
                ProcessStepType.VALIDATION,
                List.of(identityVerification, incomeVerification, surveyVerification));
    }

    private ProcessStepDTO endProcess() {
        ProcessDataInputDTO<String> verified = ProcessDataInputDTO.createEnumeratedProcessEntryValue(
                "Decyzja",
                false,
                3,
                Set.of(
                        new ProcessDataInputSelectValueDTO(ProcessDecision.CONTRACT.name()),
                        new ProcessDataInputSelectValueDTO(ProcessDecision.REJECTION.name())),
                null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab7"),
                "Zakończenie procesu",
                5,
                ProcessStepType.FINAL_STEP,
                List.of(verified));
    }
}
