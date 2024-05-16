package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface feedbackRepository extends JpaRepository<Feedback, Long> {
}
