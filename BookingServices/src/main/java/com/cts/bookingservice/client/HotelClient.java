package com.cts.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.bookingservice.dto.HotelDTO;

@FeignClient(name = "HOTELSERVICE")
public interface HotelClient {
    @GetMapping("/hotels/{hotelId}")
    HotelDTO getAllDetailsOfHotel(@PathVariable("hotelId") Long hotelId);
}