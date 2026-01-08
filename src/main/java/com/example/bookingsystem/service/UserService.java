package com.example.bookingsystem.service;

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

    public User createUser(User user) {
        // check if email is already used
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
             throw new RuntimeException("E-mail is already used for another account");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
