package com.example.servicemanagement.DTO;

import com.example.servicemanagement.Entity.UserPackage;
import com.example.servicemanagement.Entity.Package;

import java.util.Date;

public class UserPackageResponse {
    private Long userPackageId;
    private Long userId;
    private boolean status;
    private Date activationDate;
    private Date deactivationDate;
    private Package packageDetails;

    // Constructor
    public UserPackageResponse(UserPackage userPackage) {
        this.userPackageId = userPackage.getId();
        this.userId = userPackage.getUserId();
        this.status = userPackage.isStatus();
        this.activationDate = userPackage.getActivation_date();
        this.deactivationDate = userPackage.getDeactivation_date();
        this.packageDetails = userPackage.getAPackage();
    }

    // Getters and Setters
    public Long getUserPackageId() {
        return userPackageId;
    }

    public void setUserPackageId(Long userPackageId) {
        this.userPackageId = userPackageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Date deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public Package getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(Package packageDetails) {
        this.packageDetails = packageDetails;
    }
}
