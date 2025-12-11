package com.example.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
           "AND b.startTime < :endTime " +
           "AND b.endTime > :startTime")
    List<Booking> findOverlappingBookings(Long roomId, LocalDateTime startTime, LocalDateTime endTime);
}

