package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String destination;
    private String startDate;
    private String endDate;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public TourGuide getTourGuide() {
        return tourGuide;
    }

    public void setTourGuide(TourGuide tourGuide) {
        this.tourGuide = tourGuide;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JoinColumn(name = "tour_guide_id")
    private TourGuide tourGuide;

}
