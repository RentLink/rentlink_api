package com.rentlink.rentlink.manage_owner_data;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UnitOwnerEmergencyContactDTO {

    private UUID id;

    private String name;

    private String number;
}
