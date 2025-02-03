package com.cts.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{
  //custom finders methods
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
