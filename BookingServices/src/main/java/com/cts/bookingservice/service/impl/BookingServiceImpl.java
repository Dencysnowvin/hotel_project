package com.cts.bookingservice.service.impl;

import java.time.LocalDate;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.bookingservice.client.PaymentClient;
import com.cts.bookingservice.client.RoomClient;
import com.cts.bookingservice.dto.BookingDTO;
import com.cts.bookingservice.dto.PaymentDTO;
import com.cts.bookingservice.dto.RoomDTO;
import com.cts.bookingservice.entity.Booking;
import com.cts.bookingservice.exception.ResourceNotFoundException;
import com.cts.bookingservice.repo.BookingRepository;
import com.cts.bookingservice.service.BookingService;

import feign.FeignException;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
    private BookingRepository bookingRepository;
	
	@Autowired
    private RoomClient roomClient;
	
	@Autowired
    private PaymentClient paymentClient;
    @Override
    public Booking createBooking(Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        
        // Create a payment for the booking
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setBookingId(savedBooking.getBookingId());
        paymentDTO.setAmount(savedBooking.getPrice());
        paymentClient.createPayment(paymentDTO);
        
        return savedBooking;
    }


	@Override
	public List<BookingDTO> getBookingsByDate(LocalDate checkInDate) {
        List<Booking> bookings = bookingRepository.findByCheckInDate(checkInDate);
        return bookings.stream()
                       .map(this::convertToDTO)
                       .collect(Collectors.toList());
    }
    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        return convertToDTO(booking);
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // Validate room ID
        RoomDTO room;
        try {
            room = roomClient.getRoomById(bookingDTO.getRoomId());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Room not found with id: " + bookingDTO.getRoomId());
        }

        // Check room availability
        if (!isRoomAvailable(bookingDTO.getRoomId(), bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate())) {
            throw new ResourceNotFoundException("Room not available for the selected dates");
        }

        // Calculate the number of days between check-in and check-out
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());

        // Calculate the total price
        double totalPrice = room.getPrice() * daysBetween;

        // Set the price in the booking DTO
        bookingDTO.setPrice(totalPrice);

        // Convert DTO to entity
        Booking booking = convertToEntity(bookingDTO);

        // Save the booking entity to the database
        Booking savedBooking = bookingRepository.save(booking);

        // Convert the saved entity back to DTO
        return convertToDTO(savedBooking);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomClient.getAllRooms();
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDetails) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setRoomId(bookingDetails.getRoomId());
        booking.setGuestId(bookingDetails.getGuestId());
        booking.setCheckInDate(bookingDetails.getCheckInDate());
        booking.setCheckOutDate(bookingDetails.getCheckOutDate());
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToDTO(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingRepository.delete(booking);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Booking> bookings = bookingRepository.findByRoomId(roomId);
        for (Booking booking : bookings) {
            if (checkInDate.isBefore(booking.getCheckOutDate()) && checkOutDate.isAfter(booking.getCheckInDate())) {
                return false; // Room is not available
            }
        }
        return true; // Room is available
    }

    @Override
    public BookingDTO confirmBooking(Long roomId, Long guestId,LocalDate checkInDate, LocalDate checkOutDate) {
        if (isRoomAvailable(roomId, checkInDate, checkOutDate)) {
            Booking booking = new Booking();
            booking.setRoomId(roomId);
            booking.setGuestId(guestId);
            booking.setCheckInDate(checkInDate);
            booking.setCheckOutDate(checkOutDate);
            Booking savedBooking = bookingRepository.save(booking);
            return convertToDTO(savedBooking);
        } else {
            throw new RuntimeException("Room is not available for the selected dates");
        }
    }

    // Conversion methods
    @Override
    public BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setRoomId(booking.getRoomId());
        bookingDTO.setGuestId(booking.getGuestId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setPrice(booking.getPrice());
        return bookingDTO;
    }
    
    @Override
    public Booking convertToEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setRoomId(bookingDTO.getRoomId());
        booking.setGuestId(bookingDTO.getGuestId());
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setPrice(bookingDTO.getPrice());
        return booking;
    }
}

