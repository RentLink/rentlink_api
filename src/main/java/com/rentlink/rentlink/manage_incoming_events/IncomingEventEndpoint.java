package com.rentlink.rentlink.manage_incoming_events;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/incoming-events")
@RequiredArgsConstructor
public class IncomingEventEndpoint {

    private final IncomingEventExternalAPI incomingEventExternalAPI;

    @GetMapping("/")
    public List<IncomingEventDTO> getIncomingEvents(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return incomingEventExternalAPI.getIncomingEvents(accountId);
    }
}
