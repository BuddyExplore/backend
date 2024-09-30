package com.example.servicemanagement.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class UserPackage {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private Long userId;

    // Foreign key relationship to Package entity
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    @Getter
    @Setter
    @JsonManagedReference
    private Package aPackage;

    @Setter
    @Getter
    private boolean status;

    @Setter
    @Getter
    private Date activation_date;

    @Setter
    @Getter
    private Date deactivation_date;



    // Custom getter for packageId
    public Long getPackageId() {
        return this.aPackage != null ? this.aPackage.getId() : null;
    }

    // Optional setter for packageId if needed
    public void setPackageId(Long packageId) {
        if (this.aPackage == null) {
            this.aPackage = new Package();
        }
        this.aPackage.setId(packageId);
    }
}
