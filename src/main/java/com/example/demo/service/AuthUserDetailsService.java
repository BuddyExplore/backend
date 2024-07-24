package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repo.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    public AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = authUserRepository.findByEmail(email.toLowerCase());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(email);
        } else {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getEmail()) // map to username
                    .password(user.get().getPassword())
                    .authorities(Collections.emptyList()) // no authorities
                    .build();
        }
    }
}
