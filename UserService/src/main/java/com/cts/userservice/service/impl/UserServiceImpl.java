package com.cts.userservice.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cts.userservice.entity.User;
import com.cts.userservice.exception.UserNotFoundException;
import com.cts.userservice.repository.UserRepository;
import com.cts.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + userName));
    }
}