package com.example.hotelManagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "hotels")

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
        if (rooms != null) {
            for (Room room : rooms) {
                room.setHotel(this);
            }
        }
    }

}
