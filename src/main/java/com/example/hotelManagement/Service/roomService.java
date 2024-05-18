package com.example.hotelManagement.Service;

import com.example.hotelManagement.Models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotelManagement.Models.Room;
import com.example.hotelManagement.Repository.roomRepository;
import com.example.hotelManagement.Repository.reservationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class roomService {

    @Autowired
    private roomRepository roomRepository;

    private reservationRepository reservationRepository;


    public List<Room> getAvailableRooms(Long hotelId) {
        return roomRepository.findByHotel_IdAndIsAvailable(hotelId, true);
    }

    public boolean bookRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && room.isAvailable()) {
            // Update room availability
            room.setAvailable(false);
            roomRepository.save(room);

            // Create a new reservation
            Reservation newReservation = new Reservation();

            // Set check-in date and check-out date
            LocalDateTime checkInDate = LocalDateTime.now().plusDays(1); // Example: set check-in date to current date and time
            LocalDateTime checkOutDate = checkInDate.plusDays(3); // Example: set check-out date to 1 day after check-in date

            // Set check-in and check-out dates in the reservation
            newReservation.setCheckInDate(checkInDate);
            newReservation.setCheckOutDate(checkOutDate);

            // Set the room for the new reservation
            newReservation.setRoom(room);

            // Save the reservation
            reservationRepository.save(newReservation);

            return true;
        }
        return false;
    }


    public boolean cancelReservation(Long roomId) {
        LocalDateTime now = LocalDateTime.now();
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && !room.isAvailable() && now.plusHours(2).isBefore(room.getReservation().getCheckInDate())) {
            room.setAvailable(true);
            roomRepository.save(room);
            reservationRepository.deleteById(room.getReservation().getId());
            return true;
        }
        return false;
    }

}
