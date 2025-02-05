package com.cts.rating.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private String ratingId;
        
	    @NotBlank(message = "User ID is mandatory")
	    private String userId;

	    @NotBlank(message = "Hotel ID is mandatory")
	    private String hotelId;

	    @Min(value = 1, message = "Rating must be at least 1")
	    @Max(value = 5, message = "Rating must be at most 5")
	    private int rating;

	    @Size(max = 500, message = "Feedback should not exceed 500 characters")
	    private String feedback;
	    
}
