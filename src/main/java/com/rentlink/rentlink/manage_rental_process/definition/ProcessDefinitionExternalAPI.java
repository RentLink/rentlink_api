package com.rentlink.rentlink.manage_rental_process.definition;

import java.util.List;
import java.util.UUID;

public interface ProcessDefinitionExternalAPI {

    List<ProcessDefinitionDTO> getDefinitions(UUID accountId);
}
