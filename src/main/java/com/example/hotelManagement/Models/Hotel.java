package com.example.hotelManagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "hotels")

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String name;
    private String address;
    private double latitude;
    private double longitude;

}
