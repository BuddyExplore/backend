package com.example.demo.service;


import com.example.demo.entity.Item;
import com.example.demo.entity.Vehicle;
import com.example.demo.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public String addNewVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "Vehicle added";
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles;
    }

//    public List<Vehicle> getDriverVehicles(long driverId) {
//        List<Vehicle> vehicles = vehicleRepository.findByDriverId(driverId);
//        return vehicles;
//    }

    public void deleteVehicle(long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }
}

//http://localhost:5001/api/travel/vehicle/allVehicles
