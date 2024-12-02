package com.example.demo.service;


import com.example.demo.dto.trip.TripDTO;
import com.example.demo.entity.TourGuide;
import com.example.demo.entity.Trip;
import com.example.demo.repo.TourGuideRepository;
import com.example.demo.repo.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourGuideService {

    @Autowired
    private TourGuideRepository tourGuideRepository;

    @Autowired
    private TripRepository tripRepository;

    public List<TourGuide> getAllTourGuides() {
        return tourGuideRepository.findAll();
    }

    public TourGuide addTourGuide(TourGuide tourGuide) {
        return tourGuideRepository.save(tourGuide);
    }

    public TourGuide addTripToTourGuide(Long tourGuideId, TripDTO tripDTO) {
        TourGuide tourGuide = tourGuideRepository.findById(tourGuideId).orElseThrow();
        Trip trip = new Trip();
        trip.setDestination(tripDTO.getDestination());
        trip.setStartDate(tripDTO.getStartDate());
        trip.setEndDate(tripDTO.getEndDate());
        trip.setTourGuide(tourGuide);
        tripRepository.save(trip);
        return tourGuide;
    }
}

