package com.rentlink.rentlink.manage_unit_data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentlink.rentlink.AbstractIntegrationTest;
import com.rentlink.rentlink.common.ErrorMessage;
import com.rentlink.rentlink.utils.DataGenerator;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

class UnitEndpointTest extends AbstractIntegrationTest implements DataGenerator<UnitDTO> {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetAll() throws JsonProcessingException {
        generateList(UnitDTO.class, 20)
                .forEach(ownerDTO -> restTemplate.postForObject("/api/tenant/", ownerDTO, UnitDTO.class));
        ResponseEntity<String> resp = restTemplate.getForEntity("/api/tenant/", String.class);
        assertNotNull(resp.getBody());
        List<UnitDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(20, ownerDTOS.size());
    }

    @Test
    void testPagination() throws JsonProcessingException {
        generateList(UnitDTO.class, 10)
                .forEach(ownerDTO -> restTemplate.postForObject("/api/unit/", ownerDTO, UnitDTO.class));
        ResponseEntity<String> resp = restTemplate.getForEntity("/api/unit?page=1&size=5", String.class);
        assertNotNull(resp.getBody());
        List<UnitDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(5, ownerDTOS.size());
    }

    @Test
    void test404() {
        ResponseEntity<ErrorMessage> resp =
                restTemplate.getForEntity("/api/unit/" + UUID.randomUUID(), ErrorMessage.class);
        assertEquals(HttpStatusCode.valueOf(404), resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals("UNIT_NOT_FOUND", resp.getBody().code());
    }

    @Test
    void testPost() throws JsonProcessingException {
        UnitDTO unitOwnerDTO = generateOne(UnitDTO.class);
        restTemplate.postForObject("/api/unit/", unitOwnerDTO, UnitDTO.class);

        ResponseEntity<String> resp = restTemplate.getForEntity("/api/unit/", String.class);
        assertNotNull(resp.getBody());
        List<UnitDTO> ownerDTOS = objectMapper.readValue(resp.getBody(), new TypeReference<>() {});
        assertEquals(1, ownerDTOS.size());
        UnitDTO responseUnitOwner = ownerDTOS.get(0);
        assertNotEquals(unitOwnerDTO.id(), responseUnitOwner.id());
        assertEquals(unitOwnerDTO.name(), responseUnitOwner.name());
        assertEquals(unitOwnerDTO.insuranceNumber(), responseUnitOwner.insuranceNumber());
        assertEquals(unitOwnerDTO.insuranceCompany(), responseUnitOwner.insuranceCompany());
        assertEquals(unitOwnerDTO.insuranceDueDate(), responseUnitOwner.insuranceDueDate());
        assertEquals(unitOwnerDTO.heatingType(), responseUnitOwner.heatingType());
        assertEquals(unitOwnerDTO.cooperativeFee(), responseUnitOwner.cooperativeFee());
        assertEquals(unitOwnerDTO.rentalFee(), responseUnitOwner.rentalFee());
        assertEquals(unitOwnerDTO.additionalInformation(), responseUnitOwner.additionalInformation());
        assertEquals(unitOwnerDTO.city(), responseUnitOwner.city());
        assertEquals(unitOwnerDTO.postalCode(), responseUnitOwner.postalCode());
        assertEquals(unitOwnerDTO.street(), responseUnitOwner.street());
        assertEquals(unitOwnerDTO.buildingNumber(), responseUnitOwner.buildingNumber());
        assertEquals(unitOwnerDTO.apartmentNumber(), responseUnitOwner.apartmentNumber());
        assertEquals(unitOwnerDTO.country(), responseUnitOwner.country());
        assertEquals(unitOwnerDTO.unitType(), responseUnitOwner.unitType());
        assertEquals(unitOwnerDTO.roomsNo(), responseUnitOwner.roomsNo());
        assertEquals(unitOwnerDTO.rentalType(), responseUnitOwner.rentalType());
        assertEquals(unitOwnerDTO.surface(), responseUnitOwner.surface());
    }

    @Test
    void testPatch() {
        UnitDTO postResponse = restTemplate.postForObject("/api/unit/", generateOne(UnitDTO.class), UnitDTO.class);
        UnitDTO getResponseAfterPost = restTemplate.getForObject("/api/unit/" + postResponse.id(), UnitDTO.class);

        assertEquals(getResponseAfterPost.id(), postResponse.id());
        assertEquals(getResponseAfterPost.name(), postResponse.name());
        assertEquals(getResponseAfterPost.insuranceNumber(), postResponse.insuranceNumber());
        assertEquals(getResponseAfterPost.insuranceCompany(), postResponse.insuranceCompany());
        assertEquals(getResponseAfterPost.insuranceDueDate(), postResponse.insuranceDueDate());
        assertEquals(getResponseAfterPost.heatingType(), postResponse.heatingType());
        assertEquals(getResponseAfterPost.cooperativeFee(), postResponse.cooperativeFee());
        assertEquals(getResponseAfterPost.rentalFee(), postResponse.rentalFee());
        assertEquals(getResponseAfterPost.additionalInformation(), postResponse.additionalInformation());
        assertEquals(getResponseAfterPost.city(), postResponse.city());
        assertEquals(getResponseAfterPost.postalCode(), postResponse.postalCode());
        assertEquals(getResponseAfterPost.street(), postResponse.street());
        assertEquals(getResponseAfterPost.buildingNumber(), postResponse.buildingNumber());
        assertEquals(getResponseAfterPost.apartmentNumber(), postResponse.apartmentNumber());
        assertEquals(getResponseAfterPost.country(), postResponse.country());
        assertEquals(getResponseAfterPost.unitType(), postResponse.unitType());
        assertEquals(getResponseAfterPost.roomsNo(), postResponse.roomsNo());
        assertEquals(getResponseAfterPost.rentalType(), postResponse.rentalType());
        assertEquals(getResponseAfterPost.surface(), postResponse.surface());

        UnitDTO patch = new UnitDTO(
                null, "TEST", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null);

        UnitDTO patchResponse = restTemplate.patchForObject("/api/unit/" + postResponse.id(), patch, UnitDTO.class);
        UnitDTO getResponseAfterPatch = restTemplate.getForObject("/api/unit/" + patchResponse.id(), UnitDTO.class);

        assertEquals(getResponseAfterPatch.id(), patchResponse.id());
        assertEquals(getResponseAfterPatch.name(), patchResponse.name());
        assertEquals(getResponseAfterPatch.insuranceNumber(), patchResponse.insuranceNumber());
        assertEquals(getResponseAfterPatch.insuranceCompany(), patchResponse.insuranceCompany());
        assertEquals(getResponseAfterPatch.insuranceDueDate(), patchResponse.insuranceDueDate());
        assertEquals(getResponseAfterPatch.heatingType(), patchResponse.heatingType());
        assertEquals(getResponseAfterPatch.cooperativeFee(), patchResponse.cooperativeFee());
        assertEquals(getResponseAfterPatch.rentalFee(), patchResponse.rentalFee());
        assertEquals(getResponseAfterPatch.additionalInformation(), patchResponse.additionalInformation());
        assertEquals(getResponseAfterPatch.city(), patchResponse.city());
        assertEquals(getResponseAfterPatch.postalCode(), patchResponse.postalCode());
        assertEquals(getResponseAfterPatch.street(), patchResponse.street());
        assertEquals(getResponseAfterPatch.buildingNumber(), patchResponse.buildingNumber());
        assertEquals(getResponseAfterPatch.apartmentNumber(), patchResponse.apartmentNumber());
        assertEquals(getResponseAfterPatch.country(), patchResponse.country());
        assertEquals(getResponseAfterPatch.unitType(), patchResponse.unitType());
        assertEquals(getResponseAfterPatch.roomsNo(), patchResponse.roomsNo());
        assertEquals(getResponseAfterPatch.rentalType(), patchResponse.rentalType());
        assertEquals(getResponseAfterPatch.surface(), patchResponse.surface());
    }

    @Test
    void testDelete() {
        UnitDTO postResponse = restTemplate.postForObject("/api/unit/", generateOne(UnitDTO.class), UnitDTO.class);

        UnitDTO getResponseAfterPost = restTemplate.getForObject("/api/unit/" + postResponse.id(), UnitDTO.class);
        assertEquals(postResponse, getResponseAfterPost);

        restTemplate.delete("/api/unit/" + postResponse.id());

        ResponseEntity<UnitDTO> getResponseAfterDelete =
                restTemplate.getForEntity("/api/unit/" + postResponse.id(), UnitDTO.class);
        assertEquals(HttpStatusCode.valueOf(404), getResponseAfterDelete.getStatusCode());
    }
}
