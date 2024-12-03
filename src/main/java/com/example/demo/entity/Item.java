package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Table(name="Items")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private float price;
    private int item_count;
    private String item_category;
    private Boolean is_available;

    private long shop_id;

//    @ManyToOne
//    @JoinColumn(name = "shop_id")
//    Shop shop;


}
