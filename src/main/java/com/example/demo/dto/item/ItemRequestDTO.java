package com.example.demo.dto.item;

import com.example.demo.dto.shop.ShopOneDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequestDTO {
    private long id;
    private String name;
    private String description;
    private Float price;
    private int item_count;
    private String item_category;
    private String cover_image;
    private Boolean is_available;

    private Long shopID;
}
