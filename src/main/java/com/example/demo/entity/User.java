package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    private String mobile_no;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean deleted = false;

}
