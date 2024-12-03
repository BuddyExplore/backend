package com.example.demo.testing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;
    private String coverImage;
}
