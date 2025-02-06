package com.cts.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Room {

    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private String roomNo;

    @Column(nullable = false)
    private RoomType roomType; // Enum for room type

    @Column(nullable = false)
    private int maxOccupancy; // Maximum number of people
    
    @Column(nullable = false)
    private double price; // Price for the room 
}