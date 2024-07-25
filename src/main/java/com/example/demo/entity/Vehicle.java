package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    private VehicleOwner owner;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "vehicle_capacity")
    private Integer vehicleCapacity;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "availability")
    private Boolean availability;
}
