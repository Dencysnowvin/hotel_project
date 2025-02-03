package com.cts.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.userservice.dto.UserDTO;
import com.cts.userservice.entity.User;
import com.cts.userservice.exception.ResourceNotFoundException;
import com.cts.userservice.repository.UserRepository;
import com.cts.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setRole(userDTO.getRole());
 
        user = userRepository.save(user);
        return convertToDTO(user);
    }
 
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return convertToDTO(user);
    }
 
    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
        return convertToDTO(user);
    }
 
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
 
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
 
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setContactNumber(user.getContactNumber());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

	
	
}
