package com.cts.bookingservice.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.bookingservice.entity.Booking;
import com.cts.bookingservice.repo.BookingRepository;
import com.cts.bookingservice.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    @Override
    public List<Booking> getAllBookingsForHotel(Long hotelId) {
        return bookingRepository.findByHotelId(hotelId);
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking booking) {
        if (bookingRepository.existsById(bookingId)) {
            booking.setBookingId(bookingId);
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id " + bookingId);
        }
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
