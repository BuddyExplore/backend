package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.repo.AuthUserRepository;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.VarList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtutils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private AuthUserRepository authRepository;

    public ResponseDTO login(LoginRequest loginRequest) {

        User user = authRepository.findByEmail(loginRequest.getEmail()).orElse(null);

        if(user != null && !user.getEnabled()) {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("User is disabled");
            responseDTO.setContent(null);

            return responseDTO;
        } else if(user != null && user.getDeleted()) {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("User was deleted");
            responseDTO.setContent(null);

            return responseDTO;
        }

        try {

            Authentication authentication;

            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String jwtToken = jwtutils.generateTokenFromUsername(userDetails);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken, user);

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Login Successful");
            responseDTO.setContent(response);

        } catch (AuthenticationException e) {
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("Bad Credentials : " + e.getMessage());
            responseDTO.setContent(null);
        }

        return responseDTO;
    }


    public ResponseDTO register (User user) {
        try {
           if(authRepository.findByEmail(user.getEmail()).isPresent()) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
               responseDTO.setMessage("Email already exists");
               throw new Exception("Email already exists");
           }

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            authRepository.save(user);

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("User registered successfully");
            responseDTO.setContent(user);

        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
        }

        return responseDTO;
    }

}
