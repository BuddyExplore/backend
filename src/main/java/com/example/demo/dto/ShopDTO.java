package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {
    private long id;
    private String name;
    private String address;
    private String city;
    private String description;
    private String phone_no;
    private String email;
    private String coverImage;
    private int rating;
}
