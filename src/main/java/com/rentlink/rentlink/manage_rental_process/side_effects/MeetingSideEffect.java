package com.rentlink.rentlink.manage_rental_process.side_effects;

import com.rentlink.rentlink.manage_incoming_events.IncomingEventDTO;
import com.rentlink.rentlink.manage_incoming_events.IncomingEventInternalAPI;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessDataInputDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessDataInputIdentifier;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MeetingSideEffect implements RentalProcessStepSideEffect {

    private final IncomingEventInternalAPI incomingEventInternalAPI;

    @Override
    public void execute(RentalProcessStepSideEffectContext context) {
        ProcessStepDTO initialStep = context.steps().stream()
                .filter(step -> step.type().equals(ProcessStepType.INITIAL_COMMUNICATION))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Initial step not found"));
        String name = (String) initialStep.inputs().stream()
                .filter(input -> input.identifier().equals(ProcessDataInputIdentifier.NAME))
                .map(ProcessDataInputDTO::value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Name input not found"));
        String phone = (String) initialStep.inputs().stream()
                .filter(input -> input.identifier().equals(ProcessDataInputIdentifier.PHONE))
                .map(ProcessDataInputDTO::value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Phone input not found"));
        LocalDate date = (LocalDate) context.steps().stream()
                .filter(step -> step.type().equals(ProcessStepType.MEETING))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Meeting step not found"))
                .inputs()
                .stream()
                .filter(input -> input.identifier().equals(ProcessDataInputIdentifier.MEETING_DATE))
                .map(ProcessDataInputDTO::value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Meeting date input not found"));
        IncomingEventDTO event = IncomingEventDTO.createMeeting(
                context.accountId(),
                context.rentalProcessId(),
                "Spotkanie z %s".formatted(name),
                "Spotkanie z %s, numer telefonu: %s".formatted(name, phone),
                LocalDateTime.of(date, LocalTime.NOON));
        incomingEventInternalAPI.createIncomingEvent(event);
        log.info("Meeting side effect for step");
    }
}
