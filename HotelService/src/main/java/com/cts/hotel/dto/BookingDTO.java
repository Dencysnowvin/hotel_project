package com.cts.hotel.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;




@Data
@AllArgsConstructor
public class BookingDTO {

    
    private Long bookingId;

    
    private Long roomId;
    
    private Long guestId;

    
    private LocalDate checkInDate;

   
    private LocalDate checkOutDate;
    
   


	
}
	
    
    
// // Method to calculate total price
//    public double calculateTotalPrice() {
//        long daysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
//        return daysBetween * room.getPrice();
//    }

