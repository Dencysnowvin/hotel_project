package com.cts.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import com.cts.hotel.entities.Hotel;
import com.cts.hotel.exceptions.ResourceNotFoundException;
import com.cts.hotel.repositories.HotelRepository;
import com.cts.hotel.services.impl.HotelServiceImpl;

@SpringBootTest
public class HotelServiceApplicationTests {

	@Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private Hotel hotel;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel();
        hotel.setHotelId(1L);
        hotel.setName("Test Hotel");
        hotel.setLocation("Test Location");
        hotel.setAbout("Test About");
    }

    @Test
    public void testCreateHotel() {
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        Hotel createdHotel = hotelService.create(hotel);

        assertEquals(hotel.getHotelId(), createdHotel.getHotelId());
        assertEquals(hotel.getName(), createdHotel.getName());
        assertEquals(hotel.getLocation(), createdHotel.getLocation());
        assertEquals(hotel.getAbout(), createdHotel.getAbout());
    }

    @Test
    public void testGetHotelById() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        Hotel foundHotel = hotelService.get(1L);

        assertEquals(hotel.getHotelId(), foundHotel.getHotelId());
        assertEquals(hotel.getName(), foundHotel.getName());
        assertEquals(hotel.getLocation(), foundHotel.getLocation());
        assertEquals(hotel.getAbout(), foundHotel.getAbout());
    }

    @Test
    public void testGetHotelById_NotFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            hotelService.get(1L);
        });
    }

    @Test
    public void testGetAllHotels() {
        List<Hotel> hotels = Arrays.asList(hotel);
        when(hotelRepository.findAll()).thenReturn(hotels);

        List<Hotel> hotelList = hotelService.getAll();

        assertEquals(1, hotelList.size());
        assertEquals(hotel.getHotelId(), hotelList.get(0).getHotelId());
        assertEquals(hotel.getName(), hotelList.get(0).getName());
        assertEquals(hotel.getLocation(), hotelList.get(0).getLocation());
        assertEquals(hotel.getAbout(), hotelList.get(0).getAbout());
    }
}