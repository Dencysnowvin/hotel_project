package com.cts.rating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.rating.dto.RatingDTO;
import com.cts.rating.entities.Rating;
import com.cts.rating.repository.RatingRepository;
import com.cts.rating.services.impl.RatingServiceImpl;

@SpringBootTest
class RatingServiceApplicationTests {

	@Mock
    private RatingRepository repository;

    @InjectMocks
    private RatingServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setUserId("user1");
        ratingDTO.setHotelId("hotel1");
        ratingDTO.setRating(5);
        ratingDTO.setFeedback("Excellent!");

        Rating rating = new Rating();
        rating.setUserId("user1");
        rating.setHotelId("hotel1");
        rating.setRating(5);
        rating.setFeedback("Excellent!");

        when(repository.save(any(Rating.class))).thenReturn(rating);

        RatingDTO result = service.create(ratingDTO);

        assertNotNull(result);
        assertEquals("user1", result.getUserId());
        assertEquals("hotel1", result.getHotelId());
        assertEquals(5, result.getRating());
        assertEquals("Excellent!", result.getFeedback());
    }

    @Test
    public void testGetRatings() {
        Rating rating1 = new Rating();
        rating1.setUserId("user1");
        rating1.setHotelId("hotel1");
        rating1.setRating(5);
        rating1.setFeedback("Excellent!");

        Rating rating2 = new Rating();
        rating2.setUserId("user2");
        rating2.setHotelId("hotel2");
        rating2.setRating(4);
        rating2.setFeedback("Very good!");

        when(repository.findAll()).thenReturn(Arrays.asList(rating1, rating2));

        List<RatingDTO> result = service.getRatings();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetRatingByUserId() {
        Rating rating = new Rating();
        rating.setUserId("user1");
        rating.setHotelId("hotel1");
        rating.setRating(5);
        rating.setFeedback("Excellent!");

        when(repository.findByUserId("user1")).thenReturn(Arrays.asList(rating));

        List<RatingDTO> result = service.getRatingByUserId("user1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUserId());
    }

    @Test
    public void testGetRatingByHotelId() {
        Rating rating = new Rating();
        rating.setUserId("user1");
        rating.setHotelId("hotel1");
        rating.setRating(5);
        rating.setFeedback("Excellent!");

        when(repository.findByHotelId("hotel1")).thenReturn(Arrays.asList(rating));

        List<RatingDTO> result = service.getRatingByHotelId("hotel1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("hotel1", result.get(0).getHotelId());
    }
}
