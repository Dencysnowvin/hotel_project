package com.cts.userservice.service;

import java.util.List;
import com.cts.userservice.entity.User;

public interface UserService {
	
	    User registerUser(User userDTO);
	    User getUserById(int userId);
	    List<User> getAllUsers();
	    User getUserByName(String username);
	
}
