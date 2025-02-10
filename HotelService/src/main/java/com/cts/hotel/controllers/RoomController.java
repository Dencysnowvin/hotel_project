
package com.cts.hotel.controllers;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.entities.Room;
import com.cts.hotel.entities.RoomType;
import com.cts.hotel.services.RoomService;
@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/type")
    public List<Room> getRoomsByType(@RequestParam RoomType roomType) {
        return roomService.getRoomsByType(roomType);
    }
    
    
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = roomService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/bookings/bydate")
    public ResponseEntity<List<BookingDTO>> getBookingsByDate(@RequestParam("checkInDate") LocalDate checkInDate) {
        List<BookingDTO> bookings = roomService.getBookingsByDate(checkInDate);
        return ResponseEntity.ok(bookings);
    }

}