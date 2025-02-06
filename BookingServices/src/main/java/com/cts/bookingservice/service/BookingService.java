package com.cts.bookingservice.service;

import java.util.List;
import java.util.Optional;
import com.cts.bookingservice.entity.Booking;

public interface BookingService {
    Booking createBooking(Booking booking);
    Optional<Booking> getBookingById(Long bookingId);
    List<Booking> getAllBookings();
    Booking updateBooking(Long bookingId, Booking booking);
    void deleteBooking(Long bookingId);
    List<Booking> getAllBookingsForHotel(Long hotelId);
    
}