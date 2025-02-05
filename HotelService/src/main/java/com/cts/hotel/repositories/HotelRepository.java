package com.cts.hotel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

	Optional<Hotel> findById(Long hotelId);
	

}
