
package com.cts.hotel.services;

import java.util.List;
import java.util.Optional;

import com.cts.hotel.entities.Hotel;


public interface HotelService {
	Hotel createHotel(Hotel hotel);
    Optional<Hotel> getHotelById(Long hotelId);
    List<Hotel> getHotelsByName(String hotelName);
    List<Hotel> getHotelsByRatings(Integer ratings);
    List<Hotel> getAllHotels();
    Hotel updateHotel(Long hotelId, Hotel hotel);
    void deleteHotel(Long hotelId);
}
