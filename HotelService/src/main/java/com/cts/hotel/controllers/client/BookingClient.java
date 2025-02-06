package com.cts.hotel.controllers.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.hotel.dto.BookingDTO;


@FeignClient(name = "BOOKINGSERVICES")
public interface BookingClient {
	
	@GetMapping("/getAll")
	public List<BookingDTO>getAllBookings();
	
	@GetMapping("/findByDate")
	List<BookingDTO> getReservationsForDate(@RequestParam("date") LocalDate checkInDate );
	
//    @GetMapping("/bookings/hotel/{hotelId")
//    List<BookingDTO> getBookingsByHotelId(@PathVariable("hotelId") Long hotelId);
//    
//    @GetMapping("/hotels/{hotelId}")
//    HotelDTO getAllDetailsOfHotel(@PathVariable("hotelId") Long hotelId);
}

