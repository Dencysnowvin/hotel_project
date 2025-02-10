package com.cts.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@NotBlank(message = "Name is mandatory")
	@Column(nullable = false)
	private String username;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Contact number is mandatory")
	@Pattern(regexp = "^\\d{10}$", message = "Contact number should be 10 digits")
	private String contactNumber;

	@Column(nullable = false)
	private String role; // admin or user

}
