package com.rentlink.rentlink.manage_incoming_events;

import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomingEventRepository extends JpaRepository<IncomingEvent, UUID> {

    Stream<IncomingEvent> findByAccountId(UUID accountId);
}
