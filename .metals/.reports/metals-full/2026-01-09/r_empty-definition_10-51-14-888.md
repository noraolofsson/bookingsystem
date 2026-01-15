error id: file://<WORKSPACE>/src/main/java/com/example/bookingsystem/controller/RoomController.java:_empty_/RestController#
file://<WORKSPACE>/src/main/java/com/example/bookingsystem/controller/RoomController.java
empty definition using pc, found symbol in pc: _empty_/RestController#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 299
uri: file://<WORKSPACE>/src/main/java/com/example/bookingsystem/controller/RoomController.java
text:
```scala
package com.example.bookingsystem.controller;

import com.example.bookingsystem.entity.Room;
import com.example.bookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControlle@@r
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
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

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/RestController#