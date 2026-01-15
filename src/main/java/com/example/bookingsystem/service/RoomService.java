package com.example.bookingsystem.service;

import com.example.bookingsystem.entity.Room;
import com.example.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookingsystem.dto.RoomRequest;
import com.example.bookingsystem.dto.RoomResponse;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    private RoomResponse mapToResponse(Room room) {
        return new RoomResponse(
            room.getId(),
            room.getName(),
            room.getCapacity(),
            room.getLocation()
        );
    }

    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room room = new Room();
        room.setName(roomRequest.name());
        room.setCapacity(roomRequest.capacity());
        room.setLocation(roomRequest.location());

        Room savedRoom = roomRepository.save(room);

        return mapToResponse(savedRoom);
    }

    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }
    
    public RoomResponse getRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        return mapToResponse(room);
    }
}
