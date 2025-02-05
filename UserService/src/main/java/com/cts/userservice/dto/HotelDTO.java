package com.cts.userservice.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
	

	private Long hotelId;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not exceed 100 characters")
    private String name;

    @NotBlank(message = "Location is mandatory")
    @Size(max = 200, message = "Location should not exceed 200 characters")
    private String location;

    @Size(max = 500, message = "About should not exceed 500 characters")
    private String about;

	    
}
