package com.cts.hotel;

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

import com.cts.hotel.controllers.client.BookingClient;
import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.entities.Room;
import com.cts.hotel.entities.RoomType;
import com.cts.hotel.repositories.RoomRepository;
import com.cts.hotel.services.impl.RoomServiceImpl;

@SpringBootTest
public class HotelServiceApplicationTests {

	@Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingClient bookingClient;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings() {
        BookingDTO booking1 = new BookingDTO(1L, 101L, 201L, LocalDate.now(), LocalDate.now().plusDays(2));
        BookingDTO booking2 = new BookingDTO(2L, 102L, 202L, LocalDate.now(), LocalDate.now().plusDays(3));
        List<BookingDTO> bookings = Arrays.asList(booking1, booking2);

        when(bookingClient.getAllBookings()).thenReturn(bookings);

        List<BookingDTO> result = roomService.getAllBookings();
        assertEquals(2, result.size());
        assertEquals(booking1, result.get(0));
        assertEquals(booking2, result.get(1));
    }

    @Test
    public void testGetRoomById() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setRoomNo("101");
        room.setRoomType(RoomType.Deluxe);
        room.setMaxOccupancy(2);
        room.setPrice(100.0);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Room result = roomService.getRoomById(1L);
        assertEquals(room, result);
    }

    @Test
    public void testGetRoomById_NotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            roomService.getRoomById(1L);
        });
    }
}