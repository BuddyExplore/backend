package com.example.demo.entity;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class TourGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "tourGuide", cascade = CascadeType.ALL)
    private List<Trip> trips;

    // Getters and Setters
}
