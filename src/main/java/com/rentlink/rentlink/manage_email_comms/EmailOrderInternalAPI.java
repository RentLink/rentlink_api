package com.rentlink.rentlink.manage_email_comms;

import java.util.UUID;

public interface EmailOrderInternalAPI {

    void acceptEmailSendOrder(UUID accountId, EmailOrderDTO emailOrderDTO);
}
