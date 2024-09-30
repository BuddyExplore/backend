package com.example.servicemanagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Package {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private double price;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private Integer validity_period;
    @Setter
    @Getter
    private boolean is_active;
    @Setter
    @Getter
    private Date created_at;
    @Setter
    @Getter
    private Date updated_at;
    @OneToMany(mappedBy = "aPackage")
    @JsonBackReference
    private List<UserPackage> userPackages;

}
