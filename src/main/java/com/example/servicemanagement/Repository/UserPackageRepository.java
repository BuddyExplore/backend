package com.example.servicemanagement.Repository;

import com.example.servicemanagement.Entity.Package;
import com.example.servicemanagement.Entity.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserPackageRepository extends JpaRepository<UserPackage, Long> {
//    Optional<UserPackage> findByUserIdAndPackageId(Long userId, Long packageId);
    List<UserPackage> findAllByUserIdAndStatus(Long userId, boolean status);
    List<UserPackage> findAllByStatus(boolean status);
    @Query("SELECT up FROM UserPackage up WHERE up.userId = :userId AND up.aPackage.id = :packageId")
    Optional<UserPackage> findByUserIdAndPackageId(@Param("userId") Long userId, @Param("packageId") Long packageId);
    Optional<Package> findPackageById(Long packageId);
    Optional<Package> save(Package userPackage);
    // Custom JPQL join query
    @Query("SELECT up FROM UserPackage up JOIN up.aPackage p WHERE p.id = :packageId")
    List<UserPackage> findByPackageId(@Param("packageId") Long packageId);

    // Join query to find UserPackages with active status
    @Query("SELECT up FROM UserPackage up JOIN up.aPackage p WHERE up.status = true AND p.is_active = true")
    List<UserPackage> findActiveUserPackages();
    List<UserPackage> findByUserId(Long userId);
    @Query("SELECT up FROM UserPackage up WHERE up.userId = :userId AND up.status = true")
    List<UserPackage> findActivePackagesByUserId(@Param("userId") Long userId);
}
