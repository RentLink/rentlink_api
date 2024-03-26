package com.rentlink.rentlink.manage_email_outbound;

import io.vavr.control.Try;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class EmailSender {

    private static final String RECOGNITION_CODE = "{{recognition_code}}";

    private final JavaMailSender mailSender;

    private final ResourceLoader resourceLoader;

    public Try<Void> executeEmailSend(InternalEmailOrderDTO emailOrderDTO, List<File> attachments) {
        return Try.run(() -> {
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setSubject(emailOrderDTO.subject());
                    helper.setFrom("support@rentlink.io");
                    helper.setTo(emailOrderDTO.email());
                    helper.setText(
                            resourceLoader
                                    .getResource("classpath:send-docs.html")
                                    .getContentAsString(StandardCharsets.UTF_8)
                                    .replace(
                                            RECOGNITION_CODE,
                                            emailOrderDTO.recognitionCode().toString()),
                            true);
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
