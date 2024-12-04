package com.example.demo.service;

import com.example.demo.entity.VehicleBooking;
import com.example.demo.repo.VehicleBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleBookingService {

    @Autowired
    private VehicleBookingRepository vehicleBookingRepository;

    public String addNewBooking(VehicleBooking vehicleBooking) {
        vehicleBookingRepository.save(vehicleBooking);
        return "Vehicle added";
    }

    public List<VehicleBooking> getAllBookings() {
        List<VehicleBooking> vehicles = vehicleBookingRepository.findAll();
        return vehicles;
    }

    public List<VehicleBooking> getDriverBookings(long driverId) {
        List<VehicleBooking> vehicles = vehicleBookingRepository.findByDriverId(driverId);
        return vehicles;
    }

    public List<VehicleBooking> getTouristBookings(long touristId) {
        List<VehicleBooking> vehicles = vehicleBookingRepository.findByTouristId(touristId);
        return vehicles;
    }

    public List<VehicleBooking> getDriverBookingsByStatus(Long driverId, int status) {
        return vehicleBookingRepository.findByDriverIdAndStatus(driverId, status);
    }

    public VehicleBooking updateBookingStatus(long bookingId, int newStatus) {
        Optional<VehicleBooking> optionalBooking = vehicleBookingRepository.findById(bookingId);
        VehicleBooking booking = optionalBooking.get();
        booking.setStatus(newStatus);
        return vehicleBookingRepository.save(booking);

    }

    public void deleteBooking(long vehicleBookingId) {
        vehicleBookingRepository.deleteById(vehicleBookingId);
    }

    
}

// http://localhost:5001/api/travel/vehicle/allVehicles
