package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TourGuideDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentType;
    private String documentUrl;

    @ManyToOne
    @JoinColumn(name = "tour_guide_id")
    private TourGuide tourGuide;

    // Getters and Setters
}
