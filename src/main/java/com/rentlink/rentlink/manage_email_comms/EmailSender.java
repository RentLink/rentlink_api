package com.rentlink.rentlink.manage_email_comms;

import io.vavr.control.Try;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class EmailSender {

    private final JavaMailSender mailSender;

    public Try<Void> executeEmailSend(InternalEmailOrderDTO emailOrderDTO, List<File> attachments) {
        return Try.run(() -> {
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setSubject(emailOrderDTO.subject());
                    helper.setFrom("office@rentlink.io");
                    helper.setTo(emailOrderDTO.email());
                    helper.setText(emailOrderDTO.message(), false);
                    attachments.forEach(file -> {
                        try {
                            helper.addAttachment(file.getName(), file);
                        } catch (MessagingException e) {
                            throw new RuntimeException("Problem with attachment %s".formatted(file.getName()), e);
                        }
                    });
                    mailSender.send(message);
                })
                .onFailure(e -> log.error("Failed to send email order", e));
    }
}
