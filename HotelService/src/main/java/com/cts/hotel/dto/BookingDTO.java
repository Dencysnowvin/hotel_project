package com.cts.hotel.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

	private Long bookingId;

	@NotNull(message = "User ID is mandatory")
	private Long userId;

	@NotNull(message = "Hotel ID is mandatory")
	private Long hotelId;

	@NotNull(message = "Room ID is mandatory")
	private Long roomId;

	@NotNull(message = "Booking date is mandatory")
	@FutureOrPresent(message = "Booking date must be in the present or future")
	private LocalDate bookingDate;

	@NotBlank(message = "Status is mandatory")
	@Size(min = 3, max = 20, message = "Status should be between 3 and 20 characters")
	private String status; // Pending, Confirmed, Cancelled


}
