package com.example.bookingsystem.dto;

public record RoomRequest(
    String name,
    int capacity,
    String location
) {}
