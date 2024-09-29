package com.example.servicemanagement.Service;

import com.example.servicemanagement.DTO.ActivePackageRequestDTO;
import com.example.servicemanagement.DTO.PackageRequestDTO;
import com.example.servicemanagement.Entity.Package;
import com.example.servicemanagement.Entity.UserPackage;
import com.example.servicemanagement.Exceptions.PackageNotFoundException;
import com.example.servicemanagement.Repository.PackageRepository;
import com.example.servicemanagement.Repository.UserPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    private UserPackageRepository userPackageRepository;
    @Autowired
    private PackageRepository packageRepository;

    
//    public UserPackage addPackage(PackageRequestDTO request) {
//        UserPackage userPackage = new UserPackage();
//        userPackage.setUserId(request.getUserId());
//        userPackage.setPackageName(request.getPackageName());
//        userPackage.setPrice(request.getPrice());
//        userPackage.setDescription(request.getDescription());
//        userPackage.setActive(true);
//        return userPackageRepository.save(userPackage);
//    }

    
    public UserPackage getPackageById(Long id) {
        return userPackageRepository.findById(id)
                .orElseThrow(() -> new PackageNotFoundException("Package not found with id: " + id));
    }

    
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }
    public Optional<Package> getAllPackagesById(Long id) {
        return packageRepository.findById(id);
    }

    public List<UserPackage> getAllActivePackages() {
        return userPackageRepository.findAllByStatus(true);
    }
//    public List<Package> getAllActivePackages() {
//        return packageRepository.findAllByStatus(true);
//    }
    public List<UserPackage> getAllActivePackages(Long userId) {
        return userPackageRepository.findAllByUserIdAndStatus(userId, true);
    }

    public Package addPackage(PackageRequestDTO packageRequestDTO) {
        Package newPackage = new Package();
        newPackage.setName(packageRequestDTO.getName());
        newPackage.setDescription(packageRequestDTO.getDescription());
        newPackage.setPrice(packageRequestDTO.getPrice());
        newPackage.setValidity_period(packageRequestDTO.getValidity_period());
        newPackage.set_active(true);
        newPackage.setCreated_at(packageRequestDTO.getCreated_at());
        newPackage.setUpdated_at(packageRequestDTO.getUpdated_at());
        return newPackage;
    }
    public Optional<Package> updatePackage(Long id, PackageRequestDTO request) {
        Package existingPackage = userPackageRepository.findPackageById(id)
                .orElseThrow(() -> new PackageNotFoundException("Package not found with id: " + id));
        existingPackage.setName(request.getName());
        existingPackage.setPrice(request.getPrice());
        existingPackage.setDescription(request.getDescription());
        return userPackageRepository.save(existingPackage);
    }

    
    public void deletePackage(Long id) {
        UserPackage existingPackage = userPackageRepository.findById(id)
                .orElseThrow(() -> new PackageNotFoundException("Package not found with id: " + id));
        userPackageRepository.delete(existingPackage);
    }

    public void activatePackage(ActivePackageRequestDTO request) {
        Package userPackage = packageRepository
                .findById(request.getPackageId())
                .orElseThrow(() -> new PackageNotFoundException("Package not found"));
        userPackage.set_active(true);
        userPackageRepository.save(userPackage);
    }

    public void deactivatePackage(ActivePackageRequestDTO request) {
        UserPackage userPackage = userPackageRepository
                .findByUserIdAndAPackage_Id(request.getUserId(), request.getPackageId())
                .orElseThrow(() -> new PackageNotFoundException("Package not found"));
        userPackage.setStatus(false);
        userPackageRepository.save(userPackage);
    }
}
