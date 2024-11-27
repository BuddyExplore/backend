package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return authUserRepository.findAll();
    }

    public List<User> getUsersByRole(Role role) {
        return authUserRepository.findByRole(role);
    }

    public User findByEmail(String email) {
        Optional<User> user = authUserRepository.findByEmail(email);
        return user.orElse(null);
    }

    public User findById(Long id) {
        Optional<User> user = authUserRepository.findById(id);
        return user.orElse(null);
    }

    public User updateUser(String email, User newUser) {
        User user = authUserRepository.findByEmail(email).orElse(null);

        if (user != null) {

            if (newUser.getFirst_name() != null && !newUser.getFirst_name().isEmpty()) {
                user.setFirst_name(newUser.getFirst_name());
            }
            if (newUser.getLast_name() != null && !newUser.getLast_name().isEmpty()) {
                user.setLast_name(newUser.getLast_name());
            }
            if (newUser.getMobile_no() != null && !newUser.getMobile_no().isEmpty()) {
                user.setMobile_no(newUser.getMobile_no());
            }
            if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
                user.setEmail(newUser.getEmail());
            }
            if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }

            // Update the display name with proper capitalization
            //user.setDisplayName(capitalizeWords(user.getFirstname() + " " + user.getLastname()));

            return authUserRepository.save(user);
        }

        return null;
    }

    /*private String capitalizeWords(String str) {
        String[] words = str.split("\\s+");
        StringBuilder capitalizedStr = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedStr.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return capitalizedStr.toString().trim();
    }*/

    public User deleteUser(String email) {

        User user = authUserRepository.findByEmail(email).orElse(null);

        if(user != null) {
            user.setDeleted(true);
            authUserRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

    public User save(User user) {
        return authUserRepository.save(user);
    }
}
