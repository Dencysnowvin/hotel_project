package com.cts.hotel.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(nullable = false)
    private Long guestId; // Reference to user

    @Column(nullable = false)
    private Long hotelId; // Reference to hotel

    @Embedded
    private Room room; // Embedded Room class

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // Enum for status
    
    
 // Method to calculate total price
    public double calculateTotalPrice() {
        long daysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return daysBetween * room.getPrice();
    }
}
