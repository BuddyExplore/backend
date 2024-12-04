package com.example.demo.repo;

import com.example.demo.entity.Vehicle;
import com.example.demo.entity.VehicleBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleBreakdownRepository extends JpaRepository<VehicleBreakdown, Long> {
    List<VehicleBreakdown> findByVehicle(Vehicle vehicle);
}
