package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.UserResponse;
import com.example.bookingsystem.dto.RoomResponse;
import com.example.bookingsystem.dto.UserRequest;
import com.example.bookingsystem.entity.Room;
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

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getName()
        );
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

        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }
}
