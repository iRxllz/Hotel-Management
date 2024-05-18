package com.siemens.hotel_reservation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siemens.hotel_reservation_system.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}