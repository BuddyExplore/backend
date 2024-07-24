package com.example.demo.dto;

import com.example.demo.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String first_name;
    private String last_name;
    private String mobile_no;
    private String email;
    private String role;
    private Boolean enabled ;
    //password and deleted status removed

//    private Boolean deleted ;
}
