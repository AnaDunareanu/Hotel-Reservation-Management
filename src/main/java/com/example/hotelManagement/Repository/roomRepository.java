package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roomRepository extends JpaRepository<Room, Long> {
}
