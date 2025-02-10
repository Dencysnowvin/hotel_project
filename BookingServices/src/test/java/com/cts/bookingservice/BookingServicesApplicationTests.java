package com.cts.bookingservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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

import com.cts.bookingservice.client.RoomClient;
import com.cts.bookingservice.dto.BookingDTO;
import com.cts.bookingservice.entity.Booking;
import com.cts.bookingservice.repo.BookingRepository;
import com.cts.bookingservice.service.impl.BookingServiceImpl;

@SpringBootTest
class BookingServicesApplicationTests {
	
	@Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomClient roomClient;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings() {
        Booking booking1 = new Booking();
        booking1.setBookingId(1L);
        booking1.setRoomId(101L);
        booking1.setGuestId(201L);
        booking1.setCheckInDate(LocalDate.now());
        booking1.setCheckOutDate(LocalDate.now().plusDays(2));

        Booking booking2 = new Booking();
        booking2.setBookingId(2L);
        booking2.setRoomId(102L);
        booking2.setGuestId(202L);
        booking2.setCheckInDate(LocalDate.now());
        booking2.setCheckOutDate(LocalDate.now().plusDays(3));

        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingRepository.findAll()).thenReturn(bookings);

        List<BookingDTO> result = bookingService.getAllBookings();
        assertEquals(2, result.size());
        assertEquals(booking1.getBookingId(), result.get(0).getBookingId());
        assertEquals(booking2.getBookingId(), result.get(1).getBookingId());
    }

    @Test
    public void testGetBookingById() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setRoomId(101L);
        booking.setGuestId(201L);
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(2));

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        BookingDTO result = bookingService.getBookingById(1L);
        assertEquals(booking.getBookingId(), result.getBookingId());
    }

    @Test
    public void testGetBookingById_NotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            bookingService.getBookingById(1L);
        });
    }

    @Test
    public void testConvertToDTO() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setRoomId(101L);
        booking.setGuestId(201L);
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(2));

        BookingDTO bookingDTO = bookingService.convertToDTO(booking);

        assertEquals(booking.getBookingId(), bookingDTO.getBookingId());
        assertEquals(booking.getRoomId(), bookingDTO.getRoomId());
        assertEquals(booking.getGuestId(), bookingDTO.getGuestId());
        assertEquals(booking.getCheckInDate(), bookingDTO.getCheckInDate());
        assertEquals(booking.getCheckOutDate(), bookingDTO.getCheckOutDate());
    }

    @Test
    public void testConvertToEntity() {
        BookingDTO bookingDTO = new BookingDTO(1L, 101L, 201L, LocalDate.now(), LocalDate.now().plusDays(2));

        Booking booking = bookingService.convertToEntity(bookingDTO);

        assertEquals(bookingDTO.getBookingId(), booking.getBookingId());
        assertEquals(bookingDTO.getRoomId(), booking.getRoomId());
        assertEquals(bookingDTO.getGuestId(), booking.getGuestId());
        assertEquals(bookingDTO.getCheckInDate(), booking.getCheckInDate());
        assertEquals(bookingDTO.getCheckOutDate(), booking.getCheckOutDate());
    }
}