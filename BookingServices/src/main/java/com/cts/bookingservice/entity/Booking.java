package com.cts.bookingservice.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    @Column(nullable = false)
    private Long roomId;
    
    @Column(nullable = false)
    private Long guestId;
    
    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;
    
    @Column(nullable = false)
    private double price; // Store the fetched price
}