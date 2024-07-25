package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.Data;

import java.util.Date;

@Data
public class SignupRequest {

    private String first_name;
    private String last_name;
    private Date dob;
    private String gender;
    private String email;
    private String password;
    private Role role;

}
