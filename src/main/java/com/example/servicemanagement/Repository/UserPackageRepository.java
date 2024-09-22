package com.example.servicemanagement.Repository;

import com.example.servicemanagement.Entity.Package;
import com.example.servicemanagement.Entity.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserPackageRepository extends JpaRepository<UserPackage, Long> {
    List<UserPackage> findAllByUserIdAndStatus(Long userId, boolean status);
    List<UserPackage> findAllByStatus(boolean status);
    Optional<UserPackage> findByUserIdAndAPackage_Id(Long userId, Long packageId);
    Optional<Package> findPackageById(Long packageId);
    Optional<Package> save(Package userPackage);
}
