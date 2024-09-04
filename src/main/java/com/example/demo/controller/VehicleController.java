package com.example.demo.controller;


import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import com.example.demo.utils.VarList;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/travel/vehicle")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    private final ResponseDTO responseDTO;

    @GetMapping(value = "/allVehicles")
    public ResponseEntity<ResponseDTO> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            if(!vehicles.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all vehicles");
                responseDTO.setContent(vehicles);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No vehicles found");
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

    @PostMapping(value = "/addVehicle")
    public ResponseEntity<ResponseDTO> addNewVehicle(@RequestBody Vehicle vehicle) {
        System.out.println("Creating vehicle");
        if(vehicle == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty vehicle object");
            responseDTO.setContent(null);
        }
        else {
            try {
                String message = vehicleService.addNewVehicle(vehicle);
                if(message != "Vehicle added successfully") {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage(message);
                    responseDTO.setContent(null);
                }
                else {
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
    @GetMapping(value = "/driverVehicles/{driver_id}")
    public ResponseEntity<ResponseDTO> getDriverVehicles(@PathVariable Long driver_id) {
        try {
            List<Vehicle> vehicles = vehicleService.getDriverVehicles(driver_id);
            if(!vehicles.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing driver vehicles");
                responseDTO.setContent(vehicles);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No vehicles found");
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

}
