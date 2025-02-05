package com.cts.userservice.entity;

import java.util.ArrayList;
import java.util.List;

import com.cts.userservice.dto.BookingDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.transaction.TransactionScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
	private String name;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valuserId")
	private String email;

	@NotBlank(message = "Contact number is mandatory")
	@Pattern(regexp = "^\\d{10}$", message = "Contact number should be 10 digits")
	private String contactNumber;

	@NotBlank(message = "Role is mandatory")
	@Pattern(regexp = "^(tenant|admin)$", message = "Role should be either 'tenant' or 'admin'")
	private String role; // tenant or admin

	@Transient
	private List<BookingDTO> bookings = new ArrayList<>();

}
