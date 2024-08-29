package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name="Vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long driver_id;
    private String type;
    private String vehicle_brand;
    private String vehicle_model;
    private String license_no;
    private boolean hasAC;
    private boolean hasRadio;
    private boolean hasSunroof;
    private boolean hasWifi;
}
