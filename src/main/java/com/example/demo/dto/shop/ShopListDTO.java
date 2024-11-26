package com.example.demo.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopListDTO {
    private long id;
    private String name;
    private String city;
    private String coverImage;
}
