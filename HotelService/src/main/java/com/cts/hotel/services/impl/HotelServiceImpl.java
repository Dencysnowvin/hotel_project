package com.cts.hotel.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cts.hotel.entities.Hotel;
import com.cts.hotel.exceptions.HotelNotFoundException;
import com.cts.hotel.repositories.HotelRepository;
import com.cts.hotel.services.HotelService;
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId);
    }
    
    @Override
    public List<Hotel> getHotelsByName(String hotelName) {
        return hotelRepository.findByHotelName(hotelName);
    }

    @Override
    public List<Hotel> getHotelsByRatings(Integer ratings) {
        return hotelRepository.findByRatings(ratings);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotel(Long hotelId, Hotel hotel) {
        if (hotelRepository.existsById(hotelId)) {
            hotel.setHotelId(hotelId);
            return hotelRepository.save(hotel);
        } else {
            throw new HotelNotFoundException("Hotel not found with id " + hotelId);
        }
    }

    @Override
    public void deleteHotel(Long hotelId) {
        if (hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
        } else {
            throw new HotelNotFoundException("Hotel not found with id " + hotelId);
        }
    }
}
