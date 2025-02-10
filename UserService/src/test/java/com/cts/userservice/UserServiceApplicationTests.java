package com.cts.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.cts.userservice.entity.User;
import com.cts.userservice.repository.UserRepository;
import com.cts.userservice.service.impl.UserServiceImpl;

@SpringBootTest
class UserServiceApplicationTests {

	

	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testuser");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testuser");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        User result = userService.getUserById(1);
        assertNull(result);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("testuser1");

        User user2 = new User();
        user2.setUserId(2);
        user2.setUsername("testuser2");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));
    }

    @Test
    public void testGetUserByName() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        User result = userService.getUserByName("testuser");
        assertEquals(user, result);
    }

    @Test
    public void testGetUserByName_NotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        User result = userService.getUserByName("testuser");
        assertNull(result);
    }
}