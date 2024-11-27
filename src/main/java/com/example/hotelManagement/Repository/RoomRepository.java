package com.example.hotelManagement.Repository;

import com.example.hotelManagement.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoomRepository extends JpaRepository<Room, Long> {
}
