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

class UnitOwnerEndpointTest extends AbstractIntegrationTest implements DataGenerator<UnitOwnerDTO> {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetAll() throws JsonProcessingException {
        generateList(UnitOwnerDTO.class, 20)
                .forEach(ownerDTO -> restTemplate.postForObject("/api/owner/", ownerDTO, UnitOwnerDTO.class));
        ResponseEntity<String> resp = restTemplate.getForEntity("/api/owner/", String.class);
        assertNotNull(resp.getBody());
        List<UnitOwnerDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(20, ownerDTOS.size());
    }

    @Test
    void testPagination() throws JsonProcessingException {
        generateList(UnitOwnerDTO.class, 10)
                .forEach(ownerDTO -> restTemplate.postForObject("/api/owner/", ownerDTO, UnitOwnerDTO.class));
        ResponseEntity<String> resp = restTemplate.getForEntity("/api/owner?page=1&size=5", String.class);
        assertNotNull(resp.getBody());
        List<UnitOwnerDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(5, ownerDTOS.size());
    }

    @Test
    void test404() {
        ResponseEntity<ErrorMessage> resp =
                restTemplate.getForEntity("/api/owner/" + UUID.randomUUID(), ErrorMessage.class);
        assertEquals(HttpStatusCode.valueOf(404), resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals("UNIT_OWNER_NOT_FOUND", resp.getBody().code());
    }

    @Test
    void testPost() throws JsonProcessingException {
        UnitOwnerDTO unitOwnerDTO = generateOne(UnitOwnerDTO.class);
        restTemplate.postForObject("/api/owner/", unitOwnerDTO, UnitOwnerDTO.class);

        ResponseEntity<String> resp = restTemplate.getForEntity("/api/owner/", String.class);
        assertNotNull(resp.getBody());
        List<UnitOwnerDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(1, ownerDTOS.size());
        UnitOwnerDTO responseUnitOwner = ownerDTOS.get(0);
        assertNotEquals(unitOwnerDTO.id(), responseUnitOwner.id());
        assertEquals(unitOwnerDTO.legalPersonality(), responseUnitOwner.legalPersonality());
        assertEquals(unitOwnerDTO.name(), responseUnitOwner.name());
        assertEquals(unitOwnerDTO.surname(), responseUnitOwner.surname());
        assertEquals(unitOwnerDTO.gender(), responseUnitOwner.gender());
        assertEquals(unitOwnerDTO.citizenship(), responseUnitOwner.citizenship());
        assertEquals(unitOwnerDTO.city(), responseUnitOwner.city());
        assertEquals(unitOwnerDTO.postalCode(), responseUnitOwner.postalCode());
        assertEquals(unitOwnerDTO.street(), responseUnitOwner.street());
        assertEquals(unitOwnerDTO.buildingNumber(), responseUnitOwner.buildingNumber());
        assertEquals(unitOwnerDTO.apartmentNumber(), responseUnitOwner.apartmentNumber());
        assertEquals(unitOwnerDTO.country(), responseUnitOwner.country());
        assertEquals(unitOwnerDTO.identityDocumentType(), responseUnitOwner.identityDocumentType());
        assertEquals(unitOwnerDTO.identityDocumentNumber(), responseUnitOwner.identityDocumentNumber());
        assertEquals(unitOwnerDTO.identityDocumentDueDate(), responseUnitOwner.identityDocumentDueDate());
        assertEquals(unitOwnerDTO.identityDocumentIssueDate(), responseUnitOwner.identityDocumentIssueDate());
        assertEquals(unitOwnerDTO.taxAccountNumber(), responseUnitOwner.taxAccountNumber());
        assertEquals(unitOwnerDTO.bankAccountNumber(), responseUnitOwner.bankAccountNumber());
        assertEquals(unitOwnerDTO.bankName(), responseUnitOwner.bankName());
        assertEquals(unitOwnerDTO.companyName(), responseUnitOwner.companyName());
        assertEquals(unitOwnerDTO.nip(), responseUnitOwner.nip());
        assertEquals(unitOwnerDTO.regon(), responseUnitOwner.regon());
        assertEquals(unitOwnerDTO.krs(), responseUnitOwner.krs());
        assertEquals(unitOwnerDTO.phoneNumber(), responseUnitOwner.phoneNumber());
        assertEquals(unitOwnerDTO.email(), responseUnitOwner.email());
        assertEquals(unitOwnerDTO.emergencyContacts(), responseUnitOwner.emergencyContacts());
    }

    @Test
    void testPatch() {
        UnitOwnerDTO postResponse =
                restTemplate.postForObject("/api/owner/", generateOne(UnitOwnerDTO.class), UnitOwnerDTO.class);
        UnitOwnerDTO getResponseAfterPost =
                restTemplate.getForObject("/api/owner/" + postResponse.id(), UnitOwnerDTO.class);

        assertEquals(getResponseAfterPost.id(), postResponse.id());
        assertEquals(getResponseAfterPost.legalPersonality(), postResponse.legalPersonality());
        assertEquals(getResponseAfterPost.name(), postResponse.name());
        assertEquals(getResponseAfterPost.surname(), postResponse.surname());
        assertEquals(getResponseAfterPost.gender(), postResponse.gender());
        assertEquals(getResponseAfterPost.citizenship(), postResponse.citizenship());
        assertEquals(getResponseAfterPost.city(), postResponse.city());
        assertEquals(getResponseAfterPost.postalCode(), postResponse.postalCode());
        assertEquals(getResponseAfterPost.street(), postResponse.street());
        assertEquals(getResponseAfterPost.buildingNumber(), postResponse.buildingNumber());
        assertEquals(getResponseAfterPost.apartmentNumber(), postResponse.apartmentNumber());
        assertEquals(getResponseAfterPost.country(), postResponse.country());
        assertEquals(getResponseAfterPost.identityDocumentType(), postResponse.identityDocumentType());
        assertEquals(getResponseAfterPost.identityDocumentNumber(), postResponse.identityDocumentNumber());
        assertEquals(getResponseAfterPost.identityDocumentDueDate(), postResponse.identityDocumentDueDate());
        assertEquals(getResponseAfterPost.identityDocumentIssueDate(), postResponse.identityDocumentIssueDate());
        assertEquals(getResponseAfterPost.taxAccountNumber(), postResponse.taxAccountNumber());
        assertEquals(getResponseAfterPost.bankAccountNumber(), postResponse.bankAccountNumber());
        assertEquals(getResponseAfterPost.bankName(), postResponse.bankName());
        assertEquals(getResponseAfterPost.companyName(), postResponse.companyName());
        assertEquals(getResponseAfterPost.nip(), postResponse.nip());
        assertEquals(getResponseAfterPost.regon(), postResponse.regon());
        assertEquals(getResponseAfterPost.krs(), postResponse.krs());
        assertEquals(getResponseAfterPost.phoneNumber(), postResponse.phoneNumber());
        assertEquals(getResponseAfterPost.email(), postResponse.email());
        assertEquals(getResponseAfterPost.emergencyContacts(), postResponse.emergencyContacts());

        UnitOwnerDTO patch = new UnitOwnerDTO(
                null, null, "TEST", "TEST", null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null);

        UnitOwnerDTO patchResponse =
                restTemplate.patchForObject("/api/owner/" + postResponse.id(), patch, UnitOwnerDTO.class);
        UnitOwnerDTO getResponseAfterPach =
                restTemplate.getForObject("/api/owner/" + patchResponse.id(), UnitOwnerDTO.class);

        assertEquals(getResponseAfterPach.id(), patchResponse.id());
        assertEquals(getResponseAfterPach.legalPersonality(), patchResponse.legalPersonality());
        assertEquals(getResponseAfterPach.name(), patchResponse.name());
        assertEquals(getResponseAfterPach.surname(), patchResponse.surname());
        assertEquals(getResponseAfterPach.gender(), patchResponse.gender());
        assertEquals(getResponseAfterPach.citizenship(), patchResponse.citizenship());
        assertEquals(getResponseAfterPach.city(), patchResponse.city());
        assertEquals(getResponseAfterPach.postalCode(), patchResponse.postalCode());
        assertEquals(getResponseAfterPach.street(), patchResponse.street());
        assertEquals(getResponseAfterPach.buildingNumber(), patchResponse.buildingNumber());
        assertEquals(getResponseAfterPach.apartmentNumber(), patchResponse.apartmentNumber());
        assertEquals(getResponseAfterPach.country(), patchResponse.country());
        assertEquals(getResponseAfterPach.identityDocumentType(), patchResponse.identityDocumentType());
        assertEquals(getResponseAfterPach.identityDocumentNumber(), patchResponse.identityDocumentNumber());
        assertEquals(getResponseAfterPach.identityDocumentDueDate(), patchResponse.identityDocumentDueDate());
        assertEquals(getResponseAfterPach.identityDocumentIssueDate(), patchResponse.identityDocumentIssueDate());
        assertEquals(getResponseAfterPach.taxAccountNumber(), patchResponse.taxAccountNumber());
        assertEquals(getResponseAfterPach.bankAccountNumber(), patchResponse.bankAccountNumber());
        assertEquals(getResponseAfterPach.bankName(), patchResponse.bankName());
        assertEquals(getResponseAfterPach.companyName(), patchResponse.companyName());
        assertEquals(getResponseAfterPach.nip(), patchResponse.nip());
        assertEquals(getResponseAfterPach.regon(), patchResponse.regon());
        assertEquals(getResponseAfterPach.krs(), patchResponse.krs());
        assertEquals(getResponseAfterPach.phoneNumber(), patchResponse.phoneNumber());
        assertEquals(getResponseAfterPach.email(), patchResponse.email());
        assertEquals(getResponseAfterPach.emergencyContacts(), patchResponse.emergencyContacts());
    }

    @Test
    void testDelete() {
        UnitOwnerDTO postResponse =
                restTemplate.postForObject("/api/owner/", generateOne(UnitOwnerDTO.class), UnitOwnerDTO.class);

        UnitOwnerDTO getResponseAfterPost =
                restTemplate.getForObject("/api/owner/" + postResponse.id(), UnitOwnerDTO.class);
        assertEquals(postResponse, getResponseAfterPost);

        restTemplate.delete("/api/owner/" + postResponse.id());

        ResponseEntity<UnitOwnerDTO> getResponseAfterDelete =
                restTemplate.getForEntity("/api/owner/" + postResponse.id(), UnitOwnerDTO.class);
        assertEquals(HttpStatusCode.valueOf(404), getResponseAfterDelete.getStatusCode());
    }
}
