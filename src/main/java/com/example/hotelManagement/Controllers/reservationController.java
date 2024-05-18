package com.example.hotelManagement.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hotelManagement.Service.roomService;
import com.example.hotelManagement.Models.Room;

import java.util.List;

@RestController
@RequestMapping("/api")
public class reservationController {

    private static final Logger logger = LoggerFactory.getLogger(reservationController.class);

    @Autowired
    private roomService roomService;

    @GetMapping("/hotel/{hotelId}/available-rooms")
    public ResponseEntity<List<Room>> getAvailableRooms(@PathVariable Long hotelId) {
        List<Room> availableRooms = roomService.getAvailableRooms(hotelId);
        if (availableRooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if no available rooms found
        }
        logger.info("Retrieved {} available rooms for hotel ID {}", availableRooms.size(), hotelId);
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }

    @PostMapping("/hotel/{hotelId}/book-room")
    public ResponseEntity<String> bookRoom(@PathVariable Long hotelId, @RequestParam Long roomId) {
        boolean bookingSuccess = roomService.bookRoom(roomId);
        if (bookingSuccess) {
            return new ResponseEntity<>("Room booked successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Room is not available for booking", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cancel-reservation/{roomId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long roomId) {
        boolean cancellationSuccess = roomService.cancelReservation(roomId);
        if (cancellationSuccess) {
            return new ResponseEntity<>("Reservation cancelled successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to cancel reservation", HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/change-reservation/{roomId}")
//    public ResponseEntity<String> changeReservation(@PathVariable Long roomId) {
//        boolean changeSuccess = roomService.changeReservation(roomId);
//        if (changeSuccess) {
//            return new ResponseEntity<>("Reservation changed successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Unable to change reservation", HttpStatus.BAD_REQUEST);
//        }
//    }

}
