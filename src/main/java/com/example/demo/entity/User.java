package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String first_name;
    private String last_name;
    private Date dob;
    private String gender;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean deleted = false;
}
