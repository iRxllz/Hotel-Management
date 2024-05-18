package com.siemens.hotel_reservation_system.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.hotel_reservation_system.model.Hotel;
import com.siemens.hotel_reservation_system.repository.HotelRepository;

@RestController
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    private static final double EARTH_RADIUS = 6371000;

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotelDetails(@PathVariable int hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found!"));
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotelsWithinRadius(
            @RequestParam(name = "userLat") double userLat,
            @RequestParam(name = "userLong") double userLong,
            @RequestParam(name = "radius") double radius) {

        List<Hotel> hotels = hotelRepository.findAll();
        List<Hotel> filteredHotels = findHotelsWithinRadius(hotels, userLat, userLong, radius);
        return filteredHotels;
    }

    public double calculateDistance(double userLat, double userLong, double hotelLat, double hotelLong) {

        double userLatInRad = Math.toRadians(userLat);
        double userLongInRad = Math.toRadians(userLong);

        double deltaLatInRad = Math.toRadians(hotelLat - userLat);
        double deltaLongInRad = Math.toRadians(hotelLong - userLong);

        double a = Math.sin(deltaLatInRad / 2) * Math.sin(deltaLatInRad / 2) + Math.cos(userLatInRad)
                * Math.cos(userLongInRad) * Math.sin(deltaLongInRad / 2) * Math.sin(deltaLongInRad / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public List<Hotel> findHotelsWithinRadius(List<Hotel> hotels, double userLat, double userLong, double radius) {
        return hotels.stream()
                .filter(hotel -> calculateDistance(userLat, userLong, hotel.getLatitude(),
                        hotel.getLongitude()) <= radius)
                .collect(Collectors.toList());
    }

}