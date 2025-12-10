package com.example.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bookingsystem.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    
}

