package com.cts.rating.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.rating.dto.RatingDTO;
import com.cts.rating.entities.Rating;
import com.cts.rating.repository.RatingRepository;
import com.cts.rating.services.RatingService;
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository repository;

    @Override
    public RatingDTO create(RatingDTO ratingDTO) {
        // Convert DTO to entity
        Rating rating = new Rating();
        rating.setUserId(ratingDTO.getUserId());
        rating.setHotelId(ratingDTO.getHotelId());
        rating.setRating(ratingDTO.getRating());
        rating.setFeedback(ratingDTO.getFeedback());

        // Save entity and convert back to DTO
        Rating savedRating = repository.save(rating);
        return convertToDTO(savedRating);
    }

    @Override
    public List<RatingDTO> getRatings() {
        return repository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<RatingDTO> getRatingByUserId(String userId) {
        return repository.findByUserId(userId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<RatingDTO> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private RatingDTO convertToDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setUserId(rating.getUserId());
        ratingDTO.setHotelId(rating.getHotelId());
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setFeedback(rating.getFeedback());
        return ratingDTO;
    }
}
