package com.cts.bookingservice.service;

import java.time.LocalDate;
import java.util.List;

import com.cts.bookingservice.dto.BookingDTO;
import com.cts.bookingservice.dto.RoomDTO;
import com.cts.bookingservice.entity.Booking;

public interface BookingService {
	List<BookingDTO> getAllBookings();

    BookingDTO getBookingById(Long id);

    BookingDTO createBooking(BookingDTO bookingDTO);

    BookingDTO updateBooking(Long id, BookingDTO bookingDetails);

    void deleteBooking(Long id);

    boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    BookingDTO confirmBooking(Long roomId,Long guestId,LocalDate checkInDate, LocalDate checkOutDate);

    // Conversion methods
    BookingDTO convertToDTO(Booking booking);

    Booking convertToEntity(BookingDTO bookingDTO);

    List<BookingDTO> getBookingsByDate(LocalDate checkInDate);
	
	List<RoomDTO> getAllRooms();

	Booking createBooking(Booking booking);



    
}