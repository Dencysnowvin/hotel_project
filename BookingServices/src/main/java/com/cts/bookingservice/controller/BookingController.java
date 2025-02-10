package com.cts.bookingservice.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.cts.bookingservice.dto.BookingDTO;
import com.cts.bookingservice.dto.RoomDTO;
import com.cts.bookingservice.service.BookingService;


@RestController
@RequestMapping("/bookings")
public class BookingController {

	    @Autowired
	    private BookingService bookingService;

	    @GetMapping
	    public List<BookingDTO> getAllBookings() {
	        return bookingService.getAllBookings();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
	        return ResponseEntity.ok(bookingService.getBookingById(id));
	    }
	    
	    @GetMapping("/rooms")
	    public ResponseEntity<List<RoomDTO>> getAllRooms() {
	        List<RoomDTO> rooms = bookingService.getAllRooms();
	        return ResponseEntity.ok(rooms);
	    }

	    @PostMapping
	    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
	        return bookingService.createBooking(bookingDTO);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDetails) {
	        return ResponseEntity.ok(bookingService.updateBooking(id, bookingDetails));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
	        bookingService.deleteBooking(id);
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/availability")
	    public ResponseEntity<Boolean> checkRoomAvailability(
	            @RequestParam Long roomId,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {
	        boolean isAvailable = bookingService.isRoomAvailable(roomId, checkInDate, checkOutDate);
	        return ResponseEntity.ok(isAvailable);
	    }

	    @PostMapping("/confirm-booking")
	    public ResponseEntity<Map<String, Object>> confirmBooking(
	            @RequestParam Long roomId,
	            @RequestParam Long guestId,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {
	        BookingDTO bookingDTO = bookingService.confirmBooking(roomId,guestId, checkInDate, checkOutDate);
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Booking confirmed");
	        response.put("booking", bookingDTO);
	        return ResponseEntity.ok(response);
	    }
	    
	    @GetMapping("/bydate")
	    public List<BookingDTO> getBookingsByDate(@RequestParam("checkInDate") LocalDate checkInDate) {
	        return bookingService.getBookingsByDate(checkInDate);
	    }
	}