package com.example.demo.repo;


import com.example.demo.entity.VehBookingDestination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface VehBookingDestinationRepository extends JpaRepository<VehBookingDestination, Long> {
    Optional<VehBookingDestination> findById(Long id);
    List<VehBookingDestination> findByBookingId(Long bookingId);
}