package com.cts.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.rating.dto.RatingDTO;

import com.cts.rating.services.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // Create rating
    @PostMapping("/create")
    public ResponseEntity<RatingDTO> create(@Valid @RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(ratingDTO));
    }

    // Get all ratings
    @GetMapping("/getAll")
    public ResponseEntity<List<RatingDTO>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    // Get ratings by user ID
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    // Get ratings by hotel ID
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}

