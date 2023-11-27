package com.rentlink.rentlink.manage_owner_data;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentlink.rentlink.AbstractIntegrationTest;
import com.rentlink.rentlink.common.ErrorMessage;
import com.rentlink.rentlink.utils.DataGenerator;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UnitOwnerEndpointTest extends AbstractIntegrationTest implements DataGenerator<UnitOwnerDTO> {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void paginationTest() throws JsonProcessingException {
        generateList(UnitOwnerDTO.class, 10)
                .forEach(ownerDTO -> restTemplate.postForObject("/api/owner/", ownerDTO, UnitOwnerDTO.class));
        ResponseEntity<String> resp = restTemplate.getForEntity("/api/owner?page=1&size=5", String.class);
        assertNotNull(resp.getBody());
        List<UnitOwnerDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(5, ownerDTOS.size());
    }

    @Test
    void getAllTest() throws JsonProcessingException {
        generateList(UnitOwnerDTO.class, 20)
                .forEach(ownerDTO -> restTemplate.postForObject("/api/owner/", ownerDTO, UnitOwnerDTO.class));
        ResponseEntity<String> resp = restTemplate.getForEntity("/api/owner/", String.class);
        assertNotNull(resp.getBody());
        List<UnitOwnerDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(20, ownerDTOS.size());
    }

    @Test
    void check404() {
        ResponseEntity<ErrorMessage> resp =
                restTemplate.getForEntity("/api/owner/" + UUID.randomUUID(), ErrorMessage.class);
        assertEquals(HttpStatusCode.valueOf(404), resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals("UNIT_OWNER_NOT_FOUND", resp.getBody().code());
    }
}
