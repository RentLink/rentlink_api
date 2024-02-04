package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process-definitions")
@RequiredArgsConstructor
public class ProcessDefinitionEndpoint {

    private final ProcessDefinitionManagement processDefinitionManagement;

    @GetMapping("/")
    List<ProcessDefinition> getDefinitions() {
        return processDefinitionManagement.getDefinitions();
    }
}