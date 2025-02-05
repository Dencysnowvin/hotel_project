package com.cts.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 // Custom query methods 
   
}
