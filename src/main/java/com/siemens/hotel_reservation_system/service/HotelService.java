package com.siemens.hotel_reservation_system.service;

// import java.util.List;
// import java.util.stream.Collectors;

// import com.siemens.hotel_reservation_system.model.Hotel;
// import com.siemens.hotel_reservation_system.repository.HotelRepository;

public class HotelService {

    // private HotelRepository hotelRepository;

    // private static final double EARTH_RADIUS = 6371000; // Earth's radius in
    // meters

    // public double calculateDistance(double userLat, double userLong, double
    // hotelLat, double hotelLong) {

    // double userLatInRad = Math.toRadians(userLat);
    // double userLongInRad = Math.toRadians(userLong);

    // double deltaLatInRad = Math.toRadians(hotelLat - userLat);
    // double deltaLongInRad = Math.toRadians(hotelLong - userLong);

    // double a = Math.sin(deltaLatInRad / 2) * Math.sin(deltaLatInRad / 2) +
    // Math.cos(userLatInRad)
    // * Math.cos(userLongInRad) * Math.sin(deltaLongInRad / 2) *
    // Math.sin(deltaLongInRad / 2);
    // double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    // return EARTH_RADIUS * c;
    // }

    // public List<Hotel> findHotelsWithinRadius(double userLat, double userLong,
    // double radius) {
    // List<Hotel> hotels = hotelRepository.findAll();
    // return hotels.stream()
    // .filter(hotel -> calculateDistance(userLat, userLong, hotel.getLatitude(),
    // hotel.getLongitude()) <= radius)
    // .collect(Collectors.toList());
    // }

}