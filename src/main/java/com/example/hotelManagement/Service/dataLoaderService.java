package com.example.hotelManagement.Service;

import com.example.hotelManagement.Models.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.example.hotelManagement.Repository.hotelRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class dataLoaderService {
    private static final Logger logger = LoggerFactory.getLogger(dataLoaderService.class);
    private final hotelRepository hotelRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public dataLoaderService(hotelRepository hotelRepository, ObjectMapper objectMapper) {
        this.hotelRepository = hotelRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void loadHotelsFromJson() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/hotels.json");
            if (inputStream == null) {
                logger.error("Unable to find the json file");
                return;
            }
            Hotel[] hotels = objectMapper.readValue(inputStream, Hotel[].class);
            Arrays.stream(hotels).forEach(hotel -> {
                if (hotel.getRooms() != null) {
                    hotel.setRooms(hotel.getRooms());
                }
                hotelRepository.save(hotel);
            });
            logger.info("Hotels data loaded successfully.");
        } catch (IOException e) {
            logger.error("Error reading hotels.json", e);
        }
    }
}
