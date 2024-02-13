package com.rentlink.rentlink.manage_rental_process;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface RentalProcessRepository extends JpaRepository<RentalProcess, UUID> {

    List<RentalProcess> findAllByRentalOptionId(UUID rentalOptionId);
}
