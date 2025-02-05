package com.cts.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.cts.userservice.dto.UserDTO;
import com.cts.userservice.entity.User;
import com.cts.userservice.exception.ResourceNotFoundException;
import com.cts.userservice.repository.UserRepository;
import com.cts.userservice.service.impl.UserServiceImpl;

@SpringBootTest
class UserServiceApplicationTests {

	

	    @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private UserServiceImpl userService;

	    private User user;
	    private UserDTO userDTO;

	    @BeforeEach
	    public void setUp() {
	        user = new User();
	        user.setUserId(1L);
	        user.setName("John Doe");
	        user.setEmail("john.doe@example.com");
	        user.setContactNumber("1234567890");
	        user.setRole("tenant");

	        userDTO = new UserDTO();
	        userDTO.setUserId(1L);
	        userDTO.setName("John Doe");
	        userDTO.setEmail("john.doe@example.com");
	        userDTO.setContactNumber("1234567890");
	        userDTO.setRole("tenant");
	    }

	    @Test
	    public void testCreateUser() {
	        when(userRepository.save(any(User.class))).thenReturn(user);

	        UserDTO createdUser = userService.createUser(userDTO);

	        assertEquals(userDTO.getUserId(), createdUser.getUserId());
	        assertEquals(userDTO.getName(), createdUser.getName());
	        assertEquals(userDTO.getEmail(), createdUser.getEmail());
	        assertEquals(userDTO.getContactNumber(), createdUser.getContactNumber());
	        assertEquals(userDTO.getRole(), createdUser.getRole());
	    }

	    @Test
	    public void testGetUserById() {
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

	        UserDTO foundUser = userService.getUserById(1L);

	        assertEquals(userDTO.getUserId(), foundUser.getUserId());
	        assertEquals(userDTO.getName(), foundUser.getName());
	        assertEquals(userDTO.getEmail(), foundUser.getEmail());
	        assertEquals(userDTO.getContactNumber(), foundUser.getContactNumber());
	        assertEquals(userDTO.getRole(), foundUser.getRole());
	    }

	    @Test
	    public void testGetUserById_NotFound() {
	        when(userRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(ResourceNotFoundException.class, () -> {
	            userService.getUserById(1L);
	        });
	    }

	    @Test
	    public void testGetAllUsers() {
	        List<User> users = Arrays.asList(user);
	        when(userRepository.findAll()).thenReturn(users);

	        List<UserDTO> userDTOs = userService.getAllUsers();

	        assertEquals(1, userDTOs.size());
	        assertEquals(userDTO.getUserId(), userDTOs.get(0).getUserId());
	        assertEquals(userDTO.getName(), userDTOs.get(0).getName());
	        assertEquals(userDTO.getEmail(), userDTOs.get(0).getEmail());
	        assertEquals(userDTO.getContactNumber(), userDTOs.get(0).getContactNumber());
	        assertEquals(userDTO.getRole(), userDTOs.get(0).getRole());
	    }

	    @Test
	    public void testUpdateUser() {
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
	        when(userRepository.save(any(User.class))).thenReturn(user);

	        UserDTO updatedUserDTO = new UserDTO();
	        updatedUserDTO.setName("Jane Doe");
	        updatedUserDTO.setEmail("jane.doe@example.com");
	        updatedUserDTO.setContactNumber("0987654321");
	        updatedUserDTO.setRole("admin");

	        UserDTO updatedUser = userService.updateUser(1L, updatedUserDTO);

	        assertEquals(updatedUserDTO.getName(), updatedUser.getName());
	        assertEquals(updatedUserDTO.getEmail(), updatedUser.getEmail());
	        assertEquals(updatedUserDTO.getContactNumber(), updatedUser.getContactNumber());
	        assertEquals(updatedUserDTO.getRole(), updatedUser.getRole());
	    }

	    @Test
	    public void testDeleteUser() {
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

	        userService.deleteUser(1L);

	        verify(userRepository).delete(user);
	    }
	}