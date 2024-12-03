package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name="Shops")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private float rating;
}
