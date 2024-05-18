package com.example.hotelManagement.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //Check if the latitude and longitude are returned
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
