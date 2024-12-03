package com.example.demo.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name="VehicleBooking")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class VehicleBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long touristId;
    private long driverId;
    private long vehicleId;
    private Date date;
    private String pickUpLocation;
    private Date pickUpDate;
    private Time pickUpTime;
    private Date dropOffDate;
    private Time dropOffTime;
    private int passengers;
    private int distance;
    private int status; // 0- pending, 1- Accepted, -1 - Canceled, 2,3,4,5.....
}
