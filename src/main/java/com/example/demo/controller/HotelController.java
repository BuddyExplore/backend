package com.example.demo.controller;
import com.example.demo.entity.Hotel;
import com.example.demo.repo.HotelRepository;
import com.example.demo.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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