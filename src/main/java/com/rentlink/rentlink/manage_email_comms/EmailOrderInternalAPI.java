package com.rentlink.rentlink.manage_email_comms;

import java.util.List;
import java.util.UUID;

public interface EmailOrderInternalAPI {

    void acceptEmailSendOrder(UUID accountId, InternalEmailOrderDTO emailOrderDTO);

    void resendFailedEmail(InternalEmailOrderDTO internalEmailOrderDTO);

    List<InternalEmailOrderDTO> getFailedEmails();
}
