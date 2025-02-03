package com.cts.rating.services;

import java.util.List;

import com.cts.rating.dto.RatingDTO;


public interface RatingService {
    // Create
    RatingDTO create(RatingDTO ratingDTO);

    // Get all ratings
    List<RatingDTO> getRatings();

    // Get ratings by userId
    List<RatingDTO> getRatingByUserId(String userId);

    // Get ratings by hotelId
    List<RatingDTO> getRatingByHotelId(String hotelId);
}
