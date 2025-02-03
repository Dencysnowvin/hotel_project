package com.cts.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.userservice.dto.UserDTO;
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
	    private UserDTO userDTO;
	 
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        user = new User();
	        user.setId(1L);
	        user.setName("John Doe");
	        user.setEmail("johndoe@example.com");
	        user.setContactNumber("123456789");
	        user.setRole("tenant");
	 
	        userDTO = new UserDTO();
	        userDTO.setName("John Doe");
	        userDTO.setEmail("johndoe@example.com");
	        userDTO.setContactNumber("123456789");
	        userDTO.setRole("tenant");
	    }
	 
	    @Test
	    public void testSaveUser() {
	        when(userRepository.save(any(User.class))).thenReturn(user);
	 
	        UserDTO savedUserDTO = userService.saveUser(userDTO);
	        assertEquals("John Doe", savedUserDTO.getName());
	    }
	 
	    @Test
	    public void testGetUserById() {
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
	 
	        UserDTO foundUserDTO = userService.getUserById(1L);
	        assertEquals("John Doe", foundUserDTO.getName());
	    }
	 
	    @Test
	    public void testDeleteUser() {
	        doNothing().when(userRepository).deleteById(1L);
	 
	        userService.deleteUser(1L);
	        verify(userRepository,times(1)).deleteById(1L);
	    }
	
}
