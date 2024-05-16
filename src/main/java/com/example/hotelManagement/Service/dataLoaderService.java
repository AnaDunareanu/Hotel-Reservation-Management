package com.example.hotelManagement.Service;

import com.example.hotelManagement.Models.Hotel;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import com.example.hotelManagement.Repository.hotelRepository;
import com.example.hotelManagement.Repository.roomRepository;

@Service
public class dataLoaderService {
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
            Hotel[] hotels = objectMapper.readValue(inputStream, Hotel[].class);
            Arrays.stream(hotels).forEach(hotelRepository::save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
