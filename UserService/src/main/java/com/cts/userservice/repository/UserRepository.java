package com.cts.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	 // Custom query methods 
	Optional<User> findByUsername(String username);
}
