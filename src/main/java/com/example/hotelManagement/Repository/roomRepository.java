package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface roomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotel_IdAndIsAvailable(long hotelId, boolean available);
}
