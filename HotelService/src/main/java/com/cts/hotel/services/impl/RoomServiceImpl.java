package com.cts.hotel.services.impl;


import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.hotel.controllers.client.BookingClient;
import com.cts.hotel.dto.BookingDTO;
import com.cts.hotel.entities.Room;
import com.cts.hotel.entities.RoomType;
import com.cts.hotel.repositories.RoomRepository;
import com.cts.hotel.services.RoomService;
@Service
public class RoomServiceImpl implements RoomService {

	    @Autowired
	    private RoomRepository roomRepository;
	    
	    @Autowired
	    private BookingClient bookingClient;
	    
	    @Override
	    public List<BookingDTO> getAllBookings() {
	        List<BookingDTO> bookings = bookingClient.getAllBookings();
	        System.out.println("Bookings: " + bookings);
	        return bookings;
	    }
	    @Override
	    public List<BookingDTO> getBookingsByDate(LocalDate checkInDate) {
	        List<BookingDTO> bookings = bookingClient.getBookingsByDate(checkInDate);
	        System.out.println("Bookings by date: " + bookings);
	        return bookings;
	    }

	    @Override
	    public List<Room> getAllRooms() {
	        return roomRepository.findAll();
	    }

	    @Override
	    public List<Room> getRoomsByType(RoomType roomType) {
	        return roomRepository.findByRoomType(roomType);
	    }
	

	    public Room getRoomById(Long id) {
	        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
	    }

	    @Override
	    public Room createRoom(Room room) {
	        return roomRepository.save(room);
	    }

	    @Override
	    public Room updateRoom(Long id, Room roomDetails) {
	        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
	        room.setRoomNo(roomDetails.getRoomNo());
	        room.setRoomType(roomDetails.getRoomType());
	        room.setMaxOccupancy(roomDetails.getMaxOccupancy());
	        room.setPrice(roomDetails.getPrice());
	        return roomRepository.save(room);
	    }

	    @Override
	    public void deleteRoom(Long id) {
	        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
	        roomRepository.delete(room);
	    }
	}