package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.VehicleBooking;
import com.example.demo.service.VehicleBookingService;
import com.example.demo.utils.VarList;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/Booking/Vehicle")
@AllArgsConstructor
public class VehicleBookingController {
    private final VehicleBookingService vehicleBookingService;
    private final ResponseDTO responseDTO;

    @GetMapping(value = "/allBookings")
    public ResponseEntity<ResponseDTO> getAllBookings() {
        try {
            List<VehicleBooking> bookings = vehicleBookingService.getAllBookings();
            if (!bookings.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all bookings");
                responseDTO.setContent(bookings);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No bookings found");
                responseDTO.setContent(null);
            }
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        }
    }

    @PostMapping(value = "/addBooking")
    public ResponseEntity<ResponseDTO> addNewBooking(@RequestBody VehicleBooking vehicleBooking) {
        System.out.println("Creating booking");
        if (vehicleBooking == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty vehicle booking object");
            responseDTO.setContent(null);
        } else {
            try {
                String message = vehicleBookingService.addNewBooking(vehicleBooking);
                if (message != "Vehicle booking added successfully") {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage(message);
                    responseDTO.setContent(null);
                } else {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage(message);
                    responseDTO.setContent(null);
                }
            } catch (Exception e) {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setContent(null);
            }
        }
        System.out.println(responseDTO.getMessage());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/driverBookings/{driver_id}")
    public ResponseEntity<ResponseDTO> getDriverBookings(@PathVariable Long driver_id) {
        try {
            List<VehicleBooking> vehicleBookings = vehicleBookingService.getDriverBookings(driver_id);
            if (!vehicleBookings.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing driver bookings");
                responseDTO.setContent(vehicleBookings);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No bookings found");
                responseDTO.setContent(null);
            }
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        }
    }

    @GetMapping(value = "/driverBookings/{driver_id}/{status}")
    public ResponseEntity<ResponseDTO> getDriverBookingsByStatus(
            @PathVariable Long driver_id, 
            @PathVariable int status) {
        try {
            List<VehicleBooking> vehicleBookings = vehicleBookingService.getDriverBookingsByStatus(driver_id, status);
            if (!vehicleBookings.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing driver bookings");
                responseDTO.setContent(vehicleBookings);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No bookings found");
                responseDTO.setContent(null);
            }
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        }
    }

    @PutMapping("/updateStatus/{booking_id}/{status}")
    public ResponseEntity<ResponseDTO> updateBookingStatus(
                                 @PathVariable Long booking_id, 
                                 @PathVariable int status) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            VehicleBooking updatedBooking = vehicleBookingService.updateBookingStatus(booking_id, status);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Booking status updated successfully");
            responseDTO.setContent(updatedBooking);
            return ResponseEntity.ok(responseDTO); 
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error occurred while updating booking status");
            responseDTO.setContent(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

}
