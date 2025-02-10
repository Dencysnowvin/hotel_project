package com.cts.hotel.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    @Column(nullable = false)
    private String roomNo;

    @Column(nullable = false)
    private RoomType roomType; // Enum for room type

    @Column(nullable = false)
    private int maxOccupancy; // Maximum number of people
    
    @Column(nullable = false)
    private double price; // per day price for the room 


}