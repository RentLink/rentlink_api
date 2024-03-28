package com.rentlink.rentlink.manage_rental_process.definition;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process-definitions")
@RequiredArgsConstructor
class ProcessDefinitionEndpoint {

    private final ProcessDefinitionExternalAPI processDefinitionManagement;

    @GetMapping("/")
    List<ProcessDefinitionDTO> getDefinitions(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return processDefinitionManagement.getDefinitions(accountId);
    }
}
