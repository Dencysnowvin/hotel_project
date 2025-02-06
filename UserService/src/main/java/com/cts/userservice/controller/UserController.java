package com.cts.userservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.userservice.entity.User;
import com.cts.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
    
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
		return ResponseEntity.ok(userService.registerUser(user));
	}

	// get single user 
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	// get all user 
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/name/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
		return ResponseEntity.ok(userService.getUserByName(userName));   
	}
}