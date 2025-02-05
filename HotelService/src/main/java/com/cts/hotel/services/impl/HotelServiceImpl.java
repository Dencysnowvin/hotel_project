package com.cts.hotel.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.hotel.controllers.client.BookingClient;
import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.dto.HotelDTO;
import com.cts.hotel.entities.Hotel;
import com.cts.hotel.exceptions.ResourceNotFoundException;
import com.cts.hotel.repositories.HotelRepository;
import com.cts.hotel.services.HotelService;
@Service
public class HotelServiceImpl implements HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Autowired
    private HotelRepository repository;
    
    @Autowired
    private BookingClient bookingClient;

    @Override
    public List<BookingDTO> getBookingsForHotel(Long hotelId) {
        return bookingClient.getBookingsByHotelId(hotelId);
    }

    @Override
    public Hotel create(Hotel hotel) {
        try {
            return repository.save(hotel);
        } catch (Exception e) {
            logger.error("Error creating hotel: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating hotel", e);
        }
    }

    @Override
    public Hotel get(Long hotelId) {
        return repository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelId));
    }

    @Override
    public List<Hotel> getAll() {
        return repository.findAll();
    }
    @Override
    public void delete(Long hotelId) {
        repository.deleteById(hotelId);
    }
    @Override
    public Hotel update(Long hotelId, Hotel hotelDetails) {
        Hotel hotel = repository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        hotel.setName(hotelDetails.getName());
        hotel.setLocation(hotelDetails.getLocation());
        hotel.setAbout(hotelDetails.getAbout());

        return repository.save(hotel);
    }
    @Override
    public HotelDTO getAllDetailsOfHotel(Long hotelId) {
        return bookingClient.getAllDetailsOfHotel(hotelId);
    }
}
