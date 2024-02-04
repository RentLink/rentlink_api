package com.rentlink.rentlink.manage_rental_process;

import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ProcessDefinitionManagement implements ProcessStepsExternalAPI {
    @Override
    public List<ProcessDefinition> getDefinitions() {
        return emptyDefinitions();
    }

    private List<ProcessDefinition> filledDefinitions() {
        return List.of(new ProcessDefinition(
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

    private List<ProcessDefinition> emptyDefinitions() {
        return List.of(
                new ProcessDefinition(
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
                new ProcessDefinition(
                        null,
                        "Skrócony proces",
                        ProcessDefinitionType.SYSTEM,
                        List.of(createInitialStep(null, null, null), createMeetStep(), endProcess()),
                        UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf")));
    }

    private ProcessStep createInitialStep(String name, String phone, String email) {
        ProcessDataInput<String> nameStep = ProcessDataInput.createLiteralProcessEntryValue("Nazwa", false, 1, name);
        ProcessDataInput<String> phoneStep =
                ProcessDataInput.createLiteralProcessEntryValue("Telefon", false, 2, phone);
        ProcessDataInput<String> emailStep = ProcessDataInput.createLiteralProcessEntryValue("E-mail", true, 3, email);
        return new ProcessStep(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffabf"),
                "Wstępny kontakt",
                1,
                List.of(nameStep, phoneStep, emailStep));
    }

    private ProcessStep createMeetStep() {
        ProcessDataInput<LocalDate> name =
                ProcessDataInput.createDateProcessEntryValue("Data spotkania", false, 1, null);
        return new ProcessStep(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab2"), "Pokazanie mieszkania", 2, List.of(name));
    }

    private ProcessStep createAwaitingDocsStep() {
        return new ProcessStep(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab1"),
                "Oczekiwanie na dokumenty",
                3,
                Collections.emptyList());
    }

    private ProcessStep createVerificationStep() {
        ProcessDataInput<String> verified = ProcessDataInput.createEnumeratedProcessEntryValue(
                "Wynik weryfikacji",
                false,
                3,
                Set.of(new ProcessDataInputSelectValue("POZYTYWNA"), new ProcessDataInputSelectValue("NEGATYWNA")),
                null);
        return new ProcessStep(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab3"), "Weryfikacja", 4, List.of(verified));
    }

    private ProcessStep endProcess() {
        ProcessDataInput<String> verified = ProcessDataInput.createEnumeratedProcessEntryValue(
                "Decyzja",
                false,
                3,
                Set.of(new ProcessDataInputSelectValue("UMOWA"), new ProcessDataInputSelectValue("KONIEC")),
                null);
        return new ProcessStep(
                UUID.fromString("e84a695e-a2c2-4cdd-b682-df81828ffab7"), "Zakończenie procesu", 5, List.of(verified));
    }
}
