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

    @PostMapping(value = "/allVehicles")
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

}
