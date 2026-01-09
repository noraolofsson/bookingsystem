package com.example.bookingsystem.dto;

public record RoomResponse(
    Long id,
    String name,
    int capacity,
    String location
) {}
