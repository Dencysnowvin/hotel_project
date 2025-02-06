package com.cts.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.userservice.dto.UserDTO;
import com.cts.userservice.entity.Role;
import com.cts.userservice.entity.User;
import com.cts.userservice.repository.UserRepository;
import com.cts.userservice.service.impl.UserServiceImpl;

@SpringBootTest
class UserServiceApplicationTests {

	

	 @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private UserServiceImpl userService;

	    private User user;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        user = new User();
	        user.setUserId(1);
	        user.setUsername("JohnDoe");
	        user.setEmail("john.doe@example.com");
	        user.setContactNumber("1234567890");
	        user.setRole(Role.Guest);
	    }

	    @Test
	    void testCreateUser() {
	        when(userRepository.save(any(User.class))).thenReturn(user);

	        User createdUser = userService.registerUser(user);

	        assertNotNull(createdUser);
	        assertEquals(user.getUsername(), createdUser.getUsername());
	        verify(userRepository, times(1)).save(user);
	    }

	    @Test
	    void testGetUserById() {
	        when(userRepository.findById(1)).thenReturn(Optional.of(user));

	        User foundUser = userService.getUserById(1);

	        assertNotNull(foundUser);
	        assertEquals(user.getUsername(), foundUser.getUsername());
	        verify(userRepository, times(1)).findById(1);
	    }

	    @Test
	    void testGetAllUsers() {
	        List<User> users = Arrays.asList(user);
	        when(userRepository.findAll()).thenReturn(users);

	        List<User> allUsers = userService.getAllUsers();

	        assertNotNull(allUsers);
	        assertEquals(1, allUsers.size());
	        verify(userRepository, times(1)).findAll();
	    }

	    @Test
	    void testGetUserByName() {
	        when(userRepository.findByUsername("JohnDoe")).thenReturn(Optional.of(user));

	        User foundUser = userService.getUserByName("JohnDoe");

	        assertNotNull(foundUser);
	        assertEquals(user.getUsername(), foundUser.getUsername());
	        verify(userRepository, times(1)).findByUsername("JohnDoe");
	    }
	}