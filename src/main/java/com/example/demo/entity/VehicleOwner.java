package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Table(name = "vehicle_owner")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VehicleOwner {

    @Id
    @Column(name = "owner_id")
    private String ownerId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private String user;
    @Enumerated(EnumType.STRING)
    private Province province;
    @Column(name = "driving_license_id")
    private String drivingLicenseId;
    @Column(name = "driving_license_pic")
    private String drivingLicensePic;
    @Builder.Default
    @Column(name = "approved_status")
    private Approval approvedStatus = Approval.PENDING;
}
