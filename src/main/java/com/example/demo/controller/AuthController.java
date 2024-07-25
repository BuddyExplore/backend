package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody User user) {
        //System.out.println(user.getDob());
        //System.out.println(user.getFirst_name());
        //System.out.println(user.getLast_name());
        //System.out.println(user.getRole());
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
