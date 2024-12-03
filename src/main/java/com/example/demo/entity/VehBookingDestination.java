package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name="VehBookingDestination")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class VehBookingDestination{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long bookingId;
    private String name;
    private float longitude;
    private float latitude;
    private boolean isDestination; //True - is a destination, False - Is Pick up location
}
