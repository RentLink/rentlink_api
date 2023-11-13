package com.rentlink.rentlink.manage_owner_data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "unit_owner_emergency_contact", schema = "rentlink")
@Setter
@Getter
@NoArgsConstructor
class UnitOwnerEmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_owner_id")
    private UnitOwner unitOwner;
}
