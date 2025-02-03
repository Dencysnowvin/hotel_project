package com.cts.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Contact number should be 10 digits")
    private String contactNumber;

    @NotBlank(message = "Role is mandatory")
    @Pattern(regexp = "^(tenant|admin)$", message = "Role should be either 'tenant' or 'admin'")
    private String role; // tenant or admin

}
