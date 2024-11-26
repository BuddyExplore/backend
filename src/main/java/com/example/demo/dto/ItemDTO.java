package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {
    private long id;
    private String name;
    private String description;
    private float price;
    private int item_count;
    private String item_category;
    private Boolean is_available;

    private Long shop_id;
}
