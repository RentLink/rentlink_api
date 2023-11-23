package com.rentlink.rentlink.manage_tenant_data;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TenantContactDetailsDTO {

    private UUID id;

    private String phoneNumber;

    private String email;
}
