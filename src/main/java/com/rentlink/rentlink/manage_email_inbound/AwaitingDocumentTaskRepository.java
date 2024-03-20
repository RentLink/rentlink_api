package com.rentlink.rentlink.manage_email_inbound;

import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwaitingDocumentTaskRepository extends JpaRepository<AwaitingDocumentTask, UUID> {

    Stream<AwaitingDocumentTask> findByStatus(AwaitingTaskStatus status);
}
