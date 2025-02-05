package com.cts.hotel.controllers.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.dto.HotelDTO;

@FeignClient(name = "BOOKINGSERVICES")
public interface BookingClient {
    @GetMapping("/bookings/hotel/{hotelId}")
    List<BookingDTO> getBookingsByHotelId(@PathVariable("hotelId") Long hotelId);
    
    @GetMapping("/hotels/{hotelId}")
    HotelDTO getAllDetailsOfHotel(@PathVariable("hotelId") Long hotelId);
}

