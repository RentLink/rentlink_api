package com.rentlink.rentlink.manage_tenant_data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class TenantContactDetailsDTO {

    private UUID id;

    private String phoneNumber;

    private String email;
}
