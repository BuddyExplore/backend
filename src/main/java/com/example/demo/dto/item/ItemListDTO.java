package com.example.demo.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemListDTO {
    private long id;
    private String name;
    private float price;
    private String item_category;
    private String cover_image;
    private Boolean is_available;
}
