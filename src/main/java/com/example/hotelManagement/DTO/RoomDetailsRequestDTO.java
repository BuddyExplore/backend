package com.example.hotelManagement.DTO;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class RoomDetailsRequestDTO implements Serializable {
    @Setter
    @Getter
    private Long userId;
    @Setter
    @Getter
    private Long packageId;

}
