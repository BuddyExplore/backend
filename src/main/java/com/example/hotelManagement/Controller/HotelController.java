package com.example.hotelManagement.Controller;

import com.example.hotelManagement.Entity.Hotel;
import com.example.hotelManagement.Repository.HotelRepository;
import com.example.hotelManagement.Service.HotelService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Hotel Management Controller")
@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/getAllHotelDetails")
    public List<Hotel> getAllHotelDetails() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }

}