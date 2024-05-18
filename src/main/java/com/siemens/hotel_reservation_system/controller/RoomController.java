package com.siemens.hotel_reservation_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.hotel_reservation_system.model.Room;
import com.siemens.hotel_reservation_system.repository.RoomRepository;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/rooms/{roomId}/reserve")
    public Room reserveRoom(@PathVariable Integer roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && room.isAvailable()) {
            room.setAvailable(false);
            return roomRepository.save(room);
        }

        return null;
    }

    @PostMapping("/rooms/{roomId}/cancel")
    public Room cancelReservation(@PathVariable Integer roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && !room.isAvailable()) {
            room.setAvailable(true);
            return roomRepository.save(room);
        }

        return null;
    }

}