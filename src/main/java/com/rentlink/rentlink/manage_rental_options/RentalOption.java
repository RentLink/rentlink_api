package com.rentlink.rentlink.manage_rental_options;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rental_option", schema = "rentlink")
@Getter
@Setter
public class RentalOption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "unit_id")
    private UUID unitId;

    @Column(name = "name")
    private String name;
}
