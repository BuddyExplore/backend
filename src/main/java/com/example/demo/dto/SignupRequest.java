package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.Data;

import java.util.Date;

@Data
public class SignupRequest {

    private String first_name;
    private String last_name;
    private String mobile_no;
    private String email;
    private String password;
    private Role role;
}
