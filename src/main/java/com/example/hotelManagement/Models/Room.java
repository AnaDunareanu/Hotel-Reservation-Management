package com.example.hotelManagement.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int roomNumber;
    private int type; //single, double, suite, matrimonial
    private double price;

    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
