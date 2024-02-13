package com.rentlink.rentlink.manage_owner_data;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_owner_id")
    private UnitOwner unitOwner;
}
