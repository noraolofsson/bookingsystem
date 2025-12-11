package com.example.bookingsystem.service;

import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.Room;
import com.example.bookingsystem.entity.User;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookingsystem.repository.BookingRepository;
import com.example.bookingsystem.repository.RoomRepository;
import com.example.bookingsystem.repository.UserRepository;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, RoomRepository roomRepository){
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }
    
    @Transactional
    public Booking createBooking(Long userId, Long roomId, LocalDateTime startTime, LocalDateTime endTime) {
        if(endTime.isBefore(startTime)){
            throw new IllegalArgumentException("End time must be after start time");
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("can't find user"));

        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("can't find room"));

        List<Booking> overlapping = bookingRepository.findOverlappingBookings(roomId, startTime, endTime);

        if(!overlapping.isEmpty()){
            throw new RuntimeException("The room is already booked during this period");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setEndTime(endTime);
        booking.setStartTime(startTime);

        return bookingRepository.save(booking);
    }
}
