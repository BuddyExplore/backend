package com.example.demo.service;

import com.example.demo.entity.Hotel;
import com.example.demo.repo.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String createHotel(Hotel hotel) {
        if(hotel.getName() == null){
            return "Not valid";
        }
        if(hotel.getAddress() == null){
            return "Not valid";
        }
        if(hotel.getContact() == null || hotel.getContact().length()<9 || hotel.getContact().length()>10){
            return "Not valid";
        }
        if(hotel.getImageUrl() == null){
            return "Not valid";
        }

        hotel.setRating(5.0F);
        hotel.setBookingCount(0);

        hotelRepository.save(hotel);
        return "Saved";
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(long id) {
        return hotelRepository.findByHotelId(id);
    }
}
