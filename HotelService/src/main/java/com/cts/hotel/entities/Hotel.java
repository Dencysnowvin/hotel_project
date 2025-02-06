package com.cts.hotel.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hotels")
public class Hotel {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long hotelId;

	    @NotBlank(message = "Name is mandatory")
	    @Size(max = 100, message = "Name should not exceed 100 characters")
	    private String hotelName;

	    @NotBlank(message = "Location is mandatory")
	    @Size(max = 200, message = "Location should not exceed 200 characters")
	    private String location;

	    @Size(max = 500, message = "About should not exceed 500 characters")
	    private String about;
	    
	    @Min(value = 1, message = "Ratings must be between 1 and 5")
	    @Max(value = 5, message = "Ratings must be between 1 and 5")
	    private int ratings;
	    
	    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	    private List<Room> rooms;


}
