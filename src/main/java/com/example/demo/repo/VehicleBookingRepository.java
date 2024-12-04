package com.example.demo.repo;


import com.example.demo.entity.VehicleBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface VehicleBookingRepository extends JpaRepository<VehicleBooking, Long> {
    Optional<VehicleBooking> findById(Long id);

    VehicleBooking getVehicleBookingById(long id);

    List<VehicleBooking> findByDriverId(Long driverId);

    List<VehicleBooking> findByTouristId(Long touristId);

    List<VehicleBooking> findByDriverIdAndStatus(Long driverId, int status);
}