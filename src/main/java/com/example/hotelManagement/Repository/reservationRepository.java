package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface reservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);
    List<Reservation> findByCheckInDate(LocalDateTime checkInDate);
    List<Reservation> findByRoom_Hotel_Id(Long hotelId);
    List<Reservation> findByRoom_Id(Long roomId);
}
