package com.example.servicemanagement.Repository;

import com.example.servicemanagement.Entity.Package;
import com.example.servicemanagement.Entity.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PackageRepository extends JpaRepository<Package, Long> {
    Optional<Package> findById(Long id);

//    List<Package> findAllByStatus(boolean status);
//    List<UserPackage> findAllByUserIdAndStatus(Long userId, boolean status);
}
