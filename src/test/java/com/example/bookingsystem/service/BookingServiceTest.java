package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.BookingRequest;
import com.example.bookingsystem.dto.BookingResponse;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.Room;
import com.example.bookingsystem.entity.User;
import com.example.bookingsystem.repository.BookingRepository;
import com.example.bookingsystem.repository.RoomRepository;
import com.example.bookingsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingService bookingService;

    private User user;
    private Room room;
    private BookingRequest request;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@test.com");

        room = new Room();
        room.setId(1L);
        room.setName("Test room");

        LocalDateTime start = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
        LocalDateTime end = start.plusHours(1);

        request = new BookingRequest(1L, 1L, start, end);
    }

    @Test
    void createBooking_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(bookingRepository.findOverlappingBookings(any(), any(), any())).thenReturn(Collections.emptyList());
        
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> {
            Booking b = invocation.getArgument(0);
            b.setId(123L);
            return b;
        });

        BookingResponse response = bookingService.createBooking(request);

        assertNotNull(response);
        assertEquals(123L, response.id());
        assertEquals(1L, response.userId());
        
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void createBooking_ShouldFail_WhenRoomOccupied() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Booking existingBooking = new Booking();
        when(bookingRepository.findOverlappingBookings(any(), any(), any()))
                .thenReturn(List.of(existingBooking));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(request);
        });

        assertEquals("The room is already booked during this period", exception.getMessage());
        
        verify(bookingRepository, never()).save(any());
    }
}
