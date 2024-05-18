package com.example.hotelManagement.Service;

import com.example.hotelManagement.Models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotelManagement.Repository.reservationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class reservationService {

    @Autowired
    private reservationRepository reservationRepository;

    public List<Reservation> findReservationsByHotel(Long hotelId) {
        return reservationRepository.findByRoom_Hotel_Id(hotelId);
    }

    public List<Reservation> findReservationsByRoom(Long roomId) {
        return reservationRepository.findByRoom_Id(roomId);
    }

    public Reservation bookRoom(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public void cancelReservation(Long id) throws IllegalArgumentException {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + id));

        LocalDateTime now = LocalDateTime.now();
        if (now.plusHours(2).isAfter(reservation.getCheckInDate())) {
            throw new IllegalArgumentException("You can only cancel the reservation at least two hours before check-in.");
        }
        else {
            reservationRepository.deleteById(id);
        }

    }

}
