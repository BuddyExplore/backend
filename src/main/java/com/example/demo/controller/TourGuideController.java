package com.example.demo.controller;

import com.example.demo.dto.trip.TripDTO;
import com.example.demo.entity.TourGuide;
import com.example.demo.service.TourGuideService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourguides")
public class TourGuideController {

    @Autowired
    private TourGuideService tourGuideService;

    @GetMapping
    public ResponseEntity<List<TourGuide>> getAllTourGuides() {
        return ResponseEntity.ok(tourGuideService.getAllTourGuides());
    }

    @PostMapping
    public ResponseEntity<TourGuide> addTourGuide(@RequestBody TourGuide tourGuide) {
        return ResponseEntity.ok(tourGuideService.addTourGuide(tourGuide));
    }

    @PostMapping("/{id}/trips")
    public ResponseEntity<TourGuide> addTrip(@PathVariable Long id, @RequestBody TripDTO tripDTO) {
        return ResponseEntity.ok(tourGuideService.addTripToTourGuide(id, tripDTO));
    }
}
