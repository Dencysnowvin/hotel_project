package com.cts.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.cts.bookingservice.dto.HotelDTO;
import com.cts.bookingservice.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
@Validated
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	

	@PostMapping
	public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
		return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
		return ResponseEntity.ok(bookingService.getBookingById(bookingId));
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<BookingDTO>> getBookingsByHotel(@PathVariable Long hotelId) {
		return ResponseEntity.ok(bookingService.getBookingsByHotelId(hotelId));
	}

	@GetMapping("/room/{roomId}")
	public ResponseEntity<List<BookingDTO>> getBookingsByRoom(@PathVariable Long roomId) {
		return ResponseEntity.ok(bookingService.getBookingsByRoomId(roomId));
	}

	@GetMapping
	public ResponseEntity<List<BookingDTO>> getAllBookings() {
		return ResponseEntity.ok(bookingService.getAllBookings());
	}

	@PutMapping("/{bookingId}/status")
	public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId, @RequestParam String status) {
		return ResponseEntity.ok(bookingService.updateBookingStatus(bookingId, status));
	}
	
//	@GetMapping("/hotel/{hotelId}")
//    public List<BookingDTO> getBookingsByHotelId(@PathVariable Long hotelId) {
//        return bookingService.getBookingsByHotelId(hotelId);
//    }

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<String> deleteBooking(@PathVariable Long bookingId) {
		bookingService.deleteBooking(bookingId);
		return ResponseEntity.ok("Booking deleted successfully!");
	}
	
	@GetMapping("/hotels/{hotelId}")
    public HotelDTO getAllDetailsOfHotel(@PathVariable Long hotelId) {
        return bookingService.getAllDetailsOfHotel(hotelId);
    }
}