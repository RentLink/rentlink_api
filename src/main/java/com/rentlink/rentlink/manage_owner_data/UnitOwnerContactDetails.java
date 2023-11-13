package com.rentlink.rentlink.manage_owner_data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "unit_owner_contact_details", schema = "rentlink")
@Setter
@Getter
@NoArgsConstructor
class UnitOwnerContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_owner_id")
    private UnitOwner unitOwner;
}
