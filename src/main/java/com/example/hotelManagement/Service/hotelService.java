package com.example.hotelManagement.Service;

import com.example.hotelManagement.Models.Hotel;
import com.example.hotelManagement.Repository.hotelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class hotelService {
    private final hotelRepository hotelRepository;

    private static final double AVERAGE_RADIUS_OF_EARTH_KM = 6367449; // The average radius of the Earth in kilometers from the link provided

    @Autowired
    public hotelService(hotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public double calculateDistanceInMeters(double latUser, double lonUser, double latHotel, double lonHotel) {
        double latDistance = Math.toRadians(latUser - latHotel);
        double lonDistance = Math.toRadians(lonUser - lonHotel);
        double distanceInRadians = Math.sqrt(Math.pow(latDistance, 2) + Math.pow(lonDistance, 2));
        return distanceInRadians * AVERAGE_RADIUS_OF_EARTH_KM;
    }


    public List<Hotel> findNearbyHotels(double userLatitude, double userLongitude, double radius) {
        List<Hotel> nearbyHotels = new ArrayList<>();
        List<Hotel> allHotels = hotelRepository.findAll();

        for (Hotel hotel : allHotels) {
            double hotelLatitude = hotel.getLatitude();
            double hotelLongitude = hotel.getLongitude();

            // Calculate the distance between user's location and hotel's location
            double distance = calculateDistanceInMeters(userLatitude, userLongitude, hotelLatitude, hotelLongitude);

            // Check if the distance is within the specified radius
            if (distance <= radius) {
                nearbyHotels.add(hotel);
            }
        }

        return nearbyHotels;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID: " + hotelId));
    }
}
