package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hotelRepository extends JpaRepository<Hotel, Long>{

}
