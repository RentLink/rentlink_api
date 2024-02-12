package com.rentlink.rentlink.manage_rental_process;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
class ProcessDefinitionManagement implements ProcessStepsExternalAPI {
    @Override
    public List<ProcessDefinitionDTO> getDefinitions() {
        return emptyDefinitions();
    }

    private List<ProcessDefinitionDTO> filledDefinitions() {
        return List.of(new ProcessDefinitionDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffeb1"),
                "Domyślny proces",
                ProcessDefinitionType.SYSTEM,
                List.of(
                        createInitialStep("Kamil", "123123123", null),
                        createMeetStep(),
                        createAwaitingDocsStep(),
                        createVerificationStep(),
                        endProcess()),
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab2")));
    }

    private List<ProcessDefinitionDTO> emptyDefinitions() {
        return List.of(
                new ProcessDefinitionDTO(
                        null,
                        "Pełny proces",
                        ProcessDefinitionType.SYSTEM,
                        List.of(
                                createInitialStep(null, null, null),
                                createMeetStep(),
                                createAwaitingDocsStep(),
                                createVerificationStep(),
                                endProcess()),
                        UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf")),
                new ProcessDefinitionDTO(
                        null,
                        "Skrócony proces",
                        ProcessDefinitionType.SYSTEM,
                        List.of(createInitialStep(null, null, null), createMeetStep(), endProcess()),
                        UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf")));
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
                List.of(nameStep, phoneStep, emailStep));
    }

    private ProcessStepDTO createMeetStep() {
        ProcessDataInputDTO<LocalDate> name =
                ProcessDataInputDTO.createDateProcessEntryValue("Data spotkania", false, 1, null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab2"), "Pokazanie mieszkania", 2, List.of(name));
    }

    private ProcessStepDTO createAwaitingDocsStep() {
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab1"),
                "Oczekiwanie na dokumenty",
                3,
                Collections.emptyList());
    }

    private ProcessStepDTO createVerificationStep() {
        ProcessDataInputDTO<String> verified = ProcessDataInputDTO.createEnumeratedProcessEntryValue(
                "Wynik weryfikacji",
                false,
                3,
                Set.of(
                        new ProcessDataInputSelectValueDTO("POZYTYWNA"),
                        new ProcessDataInputSelectValueDTO("NEGATYWNA")),
                null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab3"), "Weryfikacja", 4, List.of(verified));
    }

    private ProcessStepDTO endProcess() {
        ProcessDataInputDTO<String> verified = ProcessDataInputDTO.createEnumeratedProcessEntryValue(
                "Decyzja",
                false,
                3,
                Set.of(new ProcessDataInputSelectValueDTO("UMOWA"), new ProcessDataInputSelectValueDTO("KONIEC")),
                null);
        return new ProcessStepDTO(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab7"), "Zakończenie procesu", 5, List.of(verified));
    }
}
