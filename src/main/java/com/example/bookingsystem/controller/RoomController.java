package com.example.bookingsystem.controller;

import com.example.bookingsystem.entity.Room;
import com.example.bookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bookingsystem.dto.RoomRequest;
import com.example.bookingsystem.dto.RoomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest) {
        return roomService.createRoom(roomRequest);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.getRoom(id);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

}
