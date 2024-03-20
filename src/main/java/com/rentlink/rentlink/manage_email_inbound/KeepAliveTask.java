package com.rentlink.rentlink.manage_email_inbound;

import com.sun.mail.imap.IMAPFolder;
import jakarta.mail.MessagingException;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeepAliveTask implements Runnable {
    private static final long KEEP_ALIVE_FREQ = 300000; // 5 minutes
    private final IMAPFolder folder;

    public KeepAliveTask(IMAPFolder folder) {
        this.folder = folder;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                LockSupport.park(KEEP_ALIVE_FREQ * 1000000L);
                // Perform a NOOP to keep the connection alive
                System.out.println("Performing a NOOP to keep the connection alive");
                folder.doCommand(protocol -> {
                    protocol.simpleCommand("NOOP", null);
                    return null;
                });
            } catch (MessagingException e) {
                log.error("Unexpected exception while keeping alive the IDLE connection", e);
            }
        }
    }
}
