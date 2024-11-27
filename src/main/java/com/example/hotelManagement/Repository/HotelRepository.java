package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
