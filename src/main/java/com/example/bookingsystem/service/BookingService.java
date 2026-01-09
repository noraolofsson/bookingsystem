package com.example.bookingsystem.service;

import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.Room;
import com.example.bookingsystem.entity.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookingsystem.repository.BookingRepository;
import com.example.bookingsystem.repository.RoomRepository;
import com.example.bookingsystem.repository.UserRepository;

import com.example.bookingsystem.dto.BookingRequest;
import com.example.bookingsystem.dto.BookingResponse;

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
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        if(bookingRequest.endTime().isBefore(bookingRequest.startTime())){
            throw new IllegalArgumentException("End time must be after start time");
        }

        User user = userRepository.findById(bookingRequest.userId())
            .orElseThrow(() -> new RuntimeException("can't find user"));

        Room room = roomRepository.findById(bookingRequest.roomId())
            .orElseThrow(() -> new RuntimeException("can't find room"));

        List<Booking> overlapping = bookingRepository.findOverlappingBookings(room.getId(), bookingRequest.startTime(), bookingRequest.endTime());

        if(!overlapping.isEmpty()){
            throw new RuntimeException("The room is already booked during this period");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setEndTime(bookingRequest.endTime());
        booking.setStartTime(bookingRequest.startTime());

        Booking savedBooking = bookingRepository.save(booking);

        return new BookingResponse(
            savedBooking.getId(),
            savedBooking.getUser().getId(),
            savedBooking.getRoom().getId(),
            savedBooking.getStartTime(),
            savedBooking.getEndTime()
        );
    }
}
