package com.siemens.hotel_reservation_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "service_rating")
    private int serviceRating;

    @Column(name = "cleanliness_rating")
    private int cleanlinessRating;

    @Column(name = "food_rating")
    private int foodRating;

    @Column(name = "comments")
    private String comments;

    public void setId(int id) {
        this.id = id;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setServiceRating(int serviceRating) {
        this.serviceRating = serviceRating;
    }

    public void setCleanlinessRating(int cleanlinessRating) {
        this.cleanlinessRating = cleanlinessRating;
    }

    public void setFoodRating(int foodRating) {
        this.foodRating = foodRating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getServiceRating() {
        return serviceRating;
    }

    public int getCleanlinessRating() {
        return cleanlinessRating;
    }

    public int getFoodRating() {
        return foodRating;
    }

    public String getComments() {
        return comments;
    }

}