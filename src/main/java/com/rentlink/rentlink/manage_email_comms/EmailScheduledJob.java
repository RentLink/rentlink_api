package com.rentlink.rentlink.manage_email_comms;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduledJob {

    private final EmailOrderManagement emailOrderManagement;

    @Scheduled(cron = "0 */15 * ? * *")
    public void sendEmails() {
        emailOrderManagement.resendFailedEmails();
    }
}
