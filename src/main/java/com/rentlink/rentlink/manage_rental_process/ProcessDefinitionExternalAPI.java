package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

interface ProcessDefinitionExternalAPI {

    List<ProcessDefinitionDTO> getDefinitions(UUID accountId);
}
