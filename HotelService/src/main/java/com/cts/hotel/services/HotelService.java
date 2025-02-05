
package com.cts.hotel.services;

import java.util.List;

import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.dto.HotelDTO;
import com.cts.hotel.entities.Hotel;


public interface HotelService {
    Hotel create(Hotel hotel);
    Hotel get(Long hotelId);
    List<Hotel> getAll();
    void delete(Long hotelId);
    List<BookingDTO> getBookingsForHotel(Long hotelId);
    Hotel update(Long hotelId, Hotel hotelDetails);
    HotelDTO getAllDetailsOfHotel(Long hotelId);

}
