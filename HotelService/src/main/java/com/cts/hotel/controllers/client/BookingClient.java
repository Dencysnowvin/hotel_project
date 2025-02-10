package com.cts.hotel.controllers.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.hotel.dto.BookingDTO;


@FeignClient(name = "BOOKINGSERVICES")
public interface BookingClient {
	
	@GetMapping("/bookings")
	public List<BookingDTO>getAllBookings();
	
	@GetMapping("/bookings/bydate")
    List<BookingDTO> getBookingsByDate(@RequestParam("checkInDate") LocalDate checkInDate);

}

