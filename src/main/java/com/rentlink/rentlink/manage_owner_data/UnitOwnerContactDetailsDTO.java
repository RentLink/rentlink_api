package com.rentlink.rentlink.manage_owner_data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class UnitOwnerContactDetailsDTO {

    private UUID id;

    private String phoneNumber;

    private String email;
}
