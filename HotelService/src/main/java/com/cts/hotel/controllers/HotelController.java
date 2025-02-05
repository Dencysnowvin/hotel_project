
package com.cts.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.dto.HotelDTO;
import com.cts.hotel.entities.Hotel;
import com.cts.hotel.services.HotelService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/hotels")
@Validated
public class HotelController {

    @Autowired
    private HotelService hotelService;
    
    

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long hotelId) {
        Hotel hotel = hotelService.get(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAll();
        return ResponseEntity.ok(hotels);
        
    }
        
     @GetMapping("/bookings/{hotelId}")
     public List<BookingDTO> getBookingsForHotel(@PathVariable Long hotelId){
         return hotelService.getBookingsForHotel(hotelId);
        }
     
     @DeleteMapping("/{hotelId}")
     public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
         hotelService.delete(hotelId);
         return ResponseEntity.noContent().build();
     }
     
     @PutMapping("/{hotelId}")
     public ResponseEntity<Hotel> updateHotelById(@PathVariable Long hotelId, @RequestBody Hotel hotelDetails) {
         Hotel updatedHotel = hotelService.update(hotelId, hotelDetails);
         return ResponseEntity.ok(updatedHotel);
     }
     @GetMapping("/details/{hotelId}")
     public ResponseEntity<HotelDTO> getAllDetailsOfHotel(@PathVariable Long hotelId) {
         HotelDTO hotel = hotelService.getAllDetailsOfHotel(hotelId);
         return ResponseEntity.ok(hotel);
     }
}