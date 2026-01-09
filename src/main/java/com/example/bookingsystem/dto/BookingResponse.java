package com.example.bookingsystem.dto;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        Long userId,
        Long roomId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}
