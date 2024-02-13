package com.rentlink.rentlink.manage_rental_process;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/process-definitions")
@RequiredArgsConstructor
class ProcessDefinitionEndpoint {

    private final ProcessDefinitionManagement processDefinitionManagement;

    @GetMapping("/")
    List<ProcessDefinitionDTO> getDefinitions() {
        return processDefinitionManagement.getDefinitions();
    }
}
