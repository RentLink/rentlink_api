package com.rentlink.rentlink.manage_rental_process;

import com.rentlink.rentlink.manage_notifications.NotificationDTO;
import com.rentlink.rentlink.manage_notifications.NotificationInternalAPI;
import com.rentlink.rentlink.manage_notifications.Priority;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalProcessMonitor {

    private final RentalProcessInternalAPI rentalProcessInternalAPI;

    private final NotificationInternalAPI notificationInternalAPI;

    @Scheduled(cron = "0 */30 * ? * *")
    public void monitor() {
        rentalProcessInternalAPI
                .findRentalProcessesUpdatedBefore(Instant.now().minus(1, ChronoUnit.DAYS))
                .forEach(rentalProcessDTO ->
                        notificationInternalAPI.createNotification(prepareNotification(rentalProcessDTO)));
    }

    private NotificationDTO prepareNotification(RentalProcessDTO rentalProcessDTO) {
        return NotificationDTO.createNewNotification(
                "Rental process is outdated",
                "Rental process with id " + rentalProcessDTO.id() + " is outdated, stuck in status "
                        + rentalProcessDTO.status() + " for more than 1 day, awaits your attention.",
                Priority.HIGH);
    }
}
