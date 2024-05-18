package com.example.hotelManagement.Controllers;

import com.example.hotelManagement.Models.Hotel;
import com.example.hotelManagement.Service.hotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class hotelController {

    private final hotelService hotelService;

    @Autowired
    public hotelController(hotelService hotelService) {
        this.hotelService = hotelService;
    }

    //Here it should have the user's current location and also enter the desired radius
    @GetMapping("/nearby")
    public String showFindHotelsPage() {
        return "findHotels";
    }


    //Here it should have the user's current location and also enter the desired radius and display the hotels
    @GetMapping("/find-hotels")
    public String findHotelsByRadius(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("radius") double radius,
            Model model
    ) {
        model.addAttribute("hotels", hotelService.findNearbyHotels(latitude, longitude, radius));
        return "nearbyHotels"; // Render the nearbyHotels.html template with the results
    }

    @GetMapping("/hotels")
    public String showAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "allHotels";
    }
}
