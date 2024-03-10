package com.rentlink.rentlink.manage_email_comms;

import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmailOrderRepository extends JpaRepository<EmailOrder, Long> {

    Stream<EmailOrder> findAllByStatus(EmailOrderStatus status);
}
