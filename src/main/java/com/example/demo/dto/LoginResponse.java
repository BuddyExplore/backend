package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Data;

import java.util.List;


@Data
public class LoginResponse {

    private String jwtToken;
    private String username;
    private List<String> roles;
    private User user;

    public LoginResponse(String username, List<String> roles, String jwtToken, User user) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
        this.user = user;
    }

}
