package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private float price;
    private int item_count;
    private String item_category;
    private Boolean is_available;

    @ManyToOne
    @JoinColumn(name = "shop_id")  // Foreign key column in Item table
    private Shop shop;
}
