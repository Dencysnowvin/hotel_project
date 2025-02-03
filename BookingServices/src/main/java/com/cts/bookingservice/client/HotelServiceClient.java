package com.cts.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.bookingservice.dto.HotelDTO;

@FeignClient(name = "hotel-service")
public interface HotelServiceClient {
    @GetMapping("/hotels/{id}")
    HotelDTO getHotelById(@PathVariable("id") long id);
}

