package com.example.demo.repo;

import com.example.demo.entity.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, String> {
}
