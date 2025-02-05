package com.cts.bookingservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
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
import com.cts.bookingservice.dto.HotelDTO;

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

    private Booking booking;
    private BookingDTO bookingDTO;
    private HotelDTO hotelDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        booking = new Booking();
        booking.setBookingId(1L);
        booking.setUserId(1L);
        booking.setHotelId(1L);
        booking.setRoomId(1L);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("Pending");

        bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(1L);
        bookingDTO.setUserId(1L);
        bookingDTO.setHotelId(1L);
        bookingDTO.setRoomId(1L);
        bookingDTO.setBookingDate(LocalDate.now());
        bookingDTO.setStatus("Pending");

      

        hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("Test Hotel");
        hotelDTO.setLocation("Test Location");
    }

    @Test
    void testCreateBooking() {
       
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);

        assertNotNull(createdBooking);
        assertEquals(1L, createdBooking.getBookingId());
        assertEquals("Pending", createdBooking.getStatus());
    }

    @Test
    void testGetBookingById() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
       

        BookingDTO foundBooking = bookingService.getBookingById(1L);

        assertNotNull(foundBooking);
        assertEquals(1L, foundBooking.getBookingId());
    }

    @Test
    void testGetBookingById_NotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookingService.getBookingById(1L));
    }

    @Test
    void testGetAllBookings() {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));

        List<BookingDTO> bookings = bookingService.getAllBookings();

        assertNotNull(bookings);
        assertEquals(1, bookings.size());
    }

    @Test
    void testUpdateBookingStatus() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDTO updatedBooking = bookingService.updateBookingStatus(1L, "Confirmed");

        assertNotNull(updatedBooking);
        assertEquals("Confirmed", updatedBooking.getStatus());
    }

    @Test
    void testDeleteBooking() {
        when(bookingRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookingRepository).deleteById(1L);

        bookingService.deleteBooking(1L);

        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBooking_NotFound() {
        when(bookingRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookingService.deleteBooking(1L));
    }
}


