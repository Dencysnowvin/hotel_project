package com.cts.bookingservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.boot.test.context.SpringBootTest;


import com.cts.bookingservice.entity.Booking;
import com.cts.bookingservice.entity.Room;
import com.cts.bookingservice.entity.RoomType;
import com.cts.bookingservice.entity.Status;
import com.cts.bookingservice.repo.BookingRepository;
import com.cts.bookingservice.service.impl.BookingServiceImpl;

@SpringBootTest
class BookingServicesApplicationTests {
	@Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Booking booking;
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room();
        room.setRoomId(1L);
        room.setRoomNo("101");
        room.setRoomType(RoomType.AC);
        room.setMaxOccupancy(2);
        room.setPrice(100.0);

        booking = new Booking();
        booking.setBookingId(1L);
        booking.setGuestId(1L);
        booking.setHotelId(1L);
        booking.setRoom(room);
        booking.setCheckInDate(LocalDate.of(2025, 2, 10));
        booking.setCheckOutDate(LocalDate.of(2025, 2, 15));
        booking.setStatus(Status.CONFIRMED);
    }

    @Test
    public void testCreateBooking() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking createdBooking = bookingService.createBooking(booking);

        assertNotNull(createdBooking);
        assertEquals(booking.getBookingId(), createdBooking.getBookingId());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    public void testGetBookingById() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Optional<Booking> foundBooking = bookingService.getBookingById(1L);

        assertTrue(foundBooking.isPresent());
        assertEquals(booking.getBookingId(), foundBooking.get().getBookingId());
        verify(bookingRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllBookings() {
        List<Booking> bookings = Arrays.asList(booking);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<Booking> allBookings = bookingService.getAllBookings();

        assertNotNull(allBookings);
        assertEquals(1, allBookings.size());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateBooking() {
        when(bookingRepository.existsById(1L)).thenReturn(true);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking updatedBooking = bookingService.updateBooking(1L, booking);

        assertNotNull(updatedBooking);
        assertEquals(booking.getBookingId(), updatedBooking.getBookingId());
        verify(bookingRepository, times(1)).existsById(1L);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    public void testDeleteBooking() {
        doNothing().when(bookingRepository).deleteById(1L);

        bookingService.deleteBooking(1L);

        verify(bookingRepository, times(1)).deleteById(1L);
    }
}