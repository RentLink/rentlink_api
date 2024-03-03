package com.rentlink.rentlink.manage_email_outbound;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmailOrderRepository extends JpaRepository<EmailOrder, UUID> {

    Stream<EmailOrder> findAllByStatus(EmailOrderStatus status);

    Optional<EmailOrder> findByAccountIdAndId(UUID accountId, UUID id);
}
