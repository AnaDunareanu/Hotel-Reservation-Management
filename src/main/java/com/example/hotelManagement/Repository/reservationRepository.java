package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface reservationRepository extends JpaRepository<Reservation, Long> {
}
