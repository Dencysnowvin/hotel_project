
package com.cts.hotel.services;

import java.time.LocalDate;
import java.util.List;

import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.entities.Room;
import com.cts.hotel.entities.RoomType;


public interface RoomService {
	List<Room> getAllRooms();

    Room getRoomById(Long id);

    Room createRoom(Room room);

    Room updateRoom(Long id, Room roomDetails);

    void deleteRoom(Long id);
    
    List<Room> getRoomsByType(RoomType roomType);
    
    List<BookingDTO> getAllBookings();
    
    List<BookingDTO> getBookingsByDate(LocalDate checkInDate); 
}