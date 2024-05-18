package com.siemens.hotel_reservation_system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.hotel_reservation_system.model.Feedback;
import com.siemens.hotel_reservation_system.model.Hotel;
import com.siemens.hotel_reservation_system.repository.FeedbackRepository;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @PostMapping("/feedback/{hotelId}")
    public ResponseEntity<Feedback> leaveFeedback(@PathVariable Long hotelId, @RequestBody Feedback feedback) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelId);
        feedback.setHotel(hotel);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        return ResponseEntity.ok(savedFeedback);
    }

    @GetMapping("/feedback/{hotelId}")
    public ResponseEntity<List<Feedback>> getFeedbackByHotelId(@PathVariable int hotelId) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(hotelId);
        if (feedbackOptional.isPresent()) {
            List<Feedback> feedbackList = new ArrayList<>();
            feedbackList.add(feedbackOptional.get());
            return ResponseEntity.ok(feedbackList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}