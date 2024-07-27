package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private float price;
    private int item_count;
    private String item_category;
    private Boolean is_available;

}
