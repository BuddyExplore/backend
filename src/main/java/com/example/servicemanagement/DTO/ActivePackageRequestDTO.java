package com.example.servicemanagement.DTO;


import lombok.Getter;
import lombok.Setter;

public class ActivePackageRequestDTO {
    @Setter
    @Getter
    private Long userId;
    @Setter
    @Getter
    private Long packageId;

}
