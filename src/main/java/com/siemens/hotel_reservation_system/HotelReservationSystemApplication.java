package com.siemens.hotel_reservation_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.siemens.hotel_reservation_system.repository")
@EntityScan(basePackages = "com.siemens.hotel_reservation_system.model")
@SpringBootApplication
public class HotelReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationSystemApplication.class, args);
	}

}

// 46.7657, 23.5943 (lat, long from home internet)
// radiuses:
// - 1 (1000) => hotel ramada
// - 1.5 (1500) => hotel ramada, hampton by hilton
// - 2 (2000) => hotel ramada, hampton by hilton, grand hotel italia

// localhost:8080/hotels?userLat=46.7657&userLong=23.5943&radius=1000