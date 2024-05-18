package com.example.hotelManagement.Service;

import com.example.hotelManagement.Models.Feedback;
import com.example.hotelManagement.Models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotelManagement.Repository.feedbackRepository;
import com.example.hotelManagement.Repository.hotelRepository;

@Service
public class feedbackService {
    @Autowired
    private feedbackRepository feedbackRepository;

    @Autowired
    private hotelRepository hotelRepository;

    public boolean submitFeedback(Long hotelId, Feedback feedback) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if (hotel != null) {
            feedback.setHotel(hotel);
            feedbackRepository.save(feedback);
            return true;
        }
        return false;
    }
}
