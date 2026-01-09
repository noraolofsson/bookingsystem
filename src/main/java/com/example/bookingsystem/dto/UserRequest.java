package com.example.bookingsystem.dto;

public record UserRequest(
    String email,
    String name
    // String password
) {}