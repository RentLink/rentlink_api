package com.rentlink.rentlink.manage_rental_process.side_effects;

import com.rentlink.rentlink.manage_email_inbound.AwaitingDocumentsInternalAPI;
import com.rentlink.rentlink.manage_email_outbound.EmailOrderInternalAPI;
import com.rentlink.rentlink.manage_email_outbound.InternalEmailOrderDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessDataInputDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessDataInputIdentifier;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SendDocumentsSideEffect implements RentalProcessStepSideEffect {

    private final AwaitingDocumentsInternalAPI awaitingDocumentsInternalAPI;

    private final EmailOrderInternalAPI emailOrderInternalAPI;

    @Override
    public void execute(RentalProcessStepSideEffectContext context) {
        ProcessStepDTO sendDocsStep = context.steps().stream()
                .filter(step -> step.type().equals(ProcessStepType.SEND_DOCS))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Send documents step not found"));
        String email = (String) sendDocsStep.inputs().stream()
                .filter(input -> input.identifier().equals(ProcessDataInputIdentifier.EMAIL))
                .map(ProcessDataInputDTO::value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Email input not found"));
        List<String> documentList = (List<String>) sendDocsStep.inputs().stream()
                .filter(input -> input.identifier().equals(ProcessDataInputIdentifier.DOC_LIST))
                .map(ProcessDataInputDTO::value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Doc list input not found"));
        awaitingDocumentsInternalAPI.acceptAwaitingDocumentTask(
                context.accountId(), context.rentalProcessId(), email, context.recognitionCode());
        emailOrderInternalAPI.acceptEmailSendOrder(
                context.accountId(),
                InternalEmailOrderDTO.orderForSendingDocumentsInRentalProcess(
                        context.accountId(), email, documentList, context.recognitionCode()));
        log.info("Send documents side effect for step");
    }
}
