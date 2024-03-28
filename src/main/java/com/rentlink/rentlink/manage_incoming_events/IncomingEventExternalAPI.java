package com.rentlink.rentlink.manage_incoming_events;

import java.util.List;
import java.util.UUID;

public interface IncomingEventExternalAPI {

    List<IncomingEventDTO> getIncomingEvents(UUID accountId);
}
