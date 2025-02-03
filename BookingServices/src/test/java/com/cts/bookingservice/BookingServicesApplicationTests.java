package com.cts.bookingservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.bookingservice.dto.BookingDTO;
import com.cts.bookingservice.entity.Booking;
import com.cts.bookingservice.exception.ResourceNotFoundException;
import com.cts.bookingservice.repo.BookingRepository;
import com.cts.bookingservice.service.impl.BookingServiceImpl;

@SpringBootTest
class BookingServicesApplicationTests {
	@Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBooking() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUserId(1L);
        bookingDTO.setHotelId(1L);
        bookingDTO.setRoomId(1L);
        bookingDTO.setBookingDate(LocalDate.now());
        bookingDTO.setStatus("Pending");

        Booking booking = new Booking();
        booking.setUserId(1L);
        booking.setHotelId(1L);
        booking.setRoomId(1L);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("Pending");

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDTO result = bookingService.createBooking(bookingDTO);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(1L, result.getHotelId());
        assertEquals(1L, result.getRoomId());
        assertEquals(LocalDate.now(), result.getBookingDate());
        assertEquals("Pending", result.getStatus());
    }

    @Test
    public void testGetBookingById() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setUserId(1L);
        booking.setHotelId(1L);
        booking.setRoomId(1L);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("Pending");

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        BookingDTO result = bookingService.getBookingById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getBookingId());
        assertEquals(1L, result.getUserId());
        assertEquals(1L, result.getHotelId());
        assertEquals(1L, result.getRoomId());
        assertEquals(LocalDate.now(), result.getBookingDate());
        assertEquals("Pending", result.getStatus());
    }

    @Test
    public void testGetBookingById_NotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookingService.getBookingById(1L);
        });
    }

    @Test
    public void testGetBookingsByUserId() {
        Booking booking1 = new Booking();
        booking1.setBookingId(1L);
        booking1.setUserId(1L);
        booking1.setHotelId(1L);
        booking1.setRoomId(1L);
        booking1.setBookingDate(LocalDate.now());
        booking1.setStatus("Pending");

        Booking booking2 = new Booking();
        booking2.setBookingId(2L);
        booking2.setUserId(1L);
        booking2.setHotelId(2L);
        booking2.setRoomId(2L);
        booking2.setBookingDate(LocalDate.now());
        booking2.setStatus("Confirmed");

        when(bookingRepository.findByUserId(1L)).thenReturn(Arrays.asList(booking1, booking2));

        List<BookingDTO> result = bookingService.getBookingsByUserId(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateBookingStatus() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setUserId(1L);
        booking.setHotelId(1L);
        booking.setRoomId(1L);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("Pending");

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDTO result = bookingService.updateBookingStatus(1L, "Confirmed");

        assertNotNull(result);
        assertEquals("Confirmed", result.getStatus());
    }

    @Test
    public void testDeleteBooking() {
        when(bookingRepository.existsById(1L)).thenReturn(true);

        bookingService.deleteBooking(1L);

        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBooking_NotFound() {
        when(bookingRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            bookingService.deleteBooking(1L);
        });
    }
}


