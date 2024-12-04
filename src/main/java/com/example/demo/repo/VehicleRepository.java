package com.example.demo.repo;

import com.example.demo.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findById(Long id);

    Vehicle getVehicleById(long id);

    List<Vehicle> findByDriverId(Long driverId);
}
