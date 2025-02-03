package com.cts.userservice.service;

import java.util.List;

import com.cts.userservice.dto.UserDTO;

public interface UserService {
	    UserDTO saveUser(UserDTO userDTO);
	    UserDTO getUserById(Long id);
	    UserDTO getUserByEmail(String email);
	    List<UserDTO> getAllUsers();
	    void deleteUser(Long id);
	    
}
