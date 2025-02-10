package com.cts.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.hotel.entities.Room;
import com.cts.hotel.entities.RoomType;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByRoomType(RoomType roomType);
}