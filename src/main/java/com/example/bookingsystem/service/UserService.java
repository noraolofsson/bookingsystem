package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.UserResponse;
import com.example.bookingsystem.dto.UserRequest;
import com.example.bookingsystem.entity.User;

import com.example.bookingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        // check if email is already used
        if (userRepository.findByEmail(userRequest.email()).isPresent()) {
             throw new RuntimeException("E-mail is already used for another account");
        }

        User user = new User();
        user.setEmail(userRequest.email());
        user.setName(userRequest.name());

        User savedUser = userRepository.save(user);

        return new UserResponse(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getName()
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
