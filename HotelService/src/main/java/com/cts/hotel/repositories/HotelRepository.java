package com.cts.hotel.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

	//Optional<Hotel> findByName(String hotelName);

	//Optional<Hotel> findById(Long hotelId);
	
	List<Hotel> findByHotelName(String hotelName);
    List<Hotel> findByRatings(int ratings);
	

}
