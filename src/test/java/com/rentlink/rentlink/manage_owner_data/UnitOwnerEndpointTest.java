package com.rentlink.rentlink.manage_owner_data;

import static org.junit.jupiter.api.Assertions.*;

import com.rentlink.rentlink.AbstractIntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UnitOwnerEndpointTest extends AbstractIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void shouldAddUnitOwner() {
        UnitOwnerDTO unitOwnerDTO = new UnitOwnerDTO();
        UnitOwnerDTO unitOwnerDTO2 = new UnitOwnerDTO();
        unitOwnerDTO2.setCity("Poland");
        UnitOwnerDTO resp = restTemplate.postForObject("/api/owner/", unitOwnerDTO, UnitOwnerDTO.class);
        assertNotNull(resp.getId());
        UnitOwnerDTO resp2 =
                restTemplate.patchForObject("/api/owner/" + resp.getId(), unitOwnerDTO2, UnitOwnerDTO.class);
        assertEquals("Poland", resp2.getCity());
    }
}
