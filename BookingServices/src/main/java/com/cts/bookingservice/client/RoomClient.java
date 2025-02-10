package com.cts.bookingservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.bookingservice.dto.RoomDTO;

@FeignClient(name = "HOTELSERVICE")
public interface RoomClient {

    @GetMapping("/rooms/{id}")
    RoomDTO getRoomById(@PathVariable("id") Long id);
    
    @GetMapping("/rooms")
    List<RoomDTO> getAllRooms();
}
