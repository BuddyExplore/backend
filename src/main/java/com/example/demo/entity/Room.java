package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Room {

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
        private double price;
        @Setter
        @Getter
        private String description;
        @Setter
        @Getter
        private Integer validity_period;
        @Setter
        @Getter
        private boolean is_active;
        @Setter
        @Getter
        private Date created_at;
        @ManyToOne
        @JoinColumn(name = "Hotel_id", referencedColumnName = "id")
        @Getter
        @Setter
        @JsonManagedReference
        private Hotel aHotel;

}
