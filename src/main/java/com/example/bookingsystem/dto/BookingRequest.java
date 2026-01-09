package com.example.bookingsystem.dto;

import java.time.LocalDateTime;

public record BookingRequest(
        Long userId,
        Long roomId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}