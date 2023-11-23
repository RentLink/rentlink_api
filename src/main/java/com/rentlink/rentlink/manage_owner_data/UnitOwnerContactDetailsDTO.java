package com.rentlink.rentlink.manage_owner_data;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UnitOwnerContactDetailsDTO {

    private UUID id;

    private String phoneNumber;

    private String email;
}
