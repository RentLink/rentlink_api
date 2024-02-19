package com.rentlink.rentlink.manage_email_comms;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailOrderManagement implements EmailOrderInternalAPI {

    //    private final JavaMailSender mailSender;
    //
    @Override
    public void acceptEmailSendOrder(EmailOrderDTO emailOrderDTO) {
        Try.run(() -> {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("office@rentlink.io");
                    message.setTo(emailOrderDTO.email());
                    message.setSubject(emailOrderDTO.subject());
                    message.setText(emailOrderDTO.message());
                    //                    mailSender.send(message);
                })
                .onFailure(e -> log.error("OUTBOX!", e));
    }
}
