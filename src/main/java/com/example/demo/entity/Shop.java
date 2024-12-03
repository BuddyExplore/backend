package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Shops")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String city;
    private String description;
    private String phone_no;
    private String email;
    private String password;
    private String coverImage;
    private int rating;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
}


//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Builder
//@Table(name="Shops")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class Shop {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//    private String name;
//    private String address;
//    private float rating;
//}
