package com.example.hotelManagement.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
public class Hotel {

        @Setter
        @Getter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        @Setter
        @Getter
        private String name;
        @Setter
        @Getter
        private String address;
        @Setter
        @Getter
        private String description;
        @Setter
        @Getter
        private String phoneNumber;
        @Setter
        @Getter
        private boolean isClosed;
        @Setter
        @Getter
        private byte stars;
        @Setter
        @Getter
        private Date addedDate;
        @OneToMany(mappedBy = "aHotel")
        @JsonManagedReference
        private List<Room> roomList;

}
