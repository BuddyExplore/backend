package com.example.servicemanagement.Controller;

import com.example.servicemanagement.DTO.ActivePackageRequestDTO;
import com.example.servicemanagement.DTO.PackageRequestDTO;
import com.example.servicemanagement.DTO.UserPackageResponse;
import com.example.servicemanagement.Entity.Package;
import com.example.servicemanagement.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.servicemanagement.Entity.UserPackage;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Tag(name = "Service Management Controller")
@RestController
@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("/api/v1/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    
//    @PostMapping("/create")
//    public ResponseEntity<UserPackage> createPackage(@RequestBody PackageRequestDTO request) {
//        UserPackage newPackage = packageService.addPackage(request);
//        return ResponseEntity.ok(newPackage);
//    }

    
    @GetMapping("/{id}")
    public ResponseEntity<UserPackage> getPackageById(@PathVariable Long id) {
        UserPackage userPackage = packageService.getPackageById(id);
        return ResponseEntity.ok(userPackage);
    }


    @GetMapping({"/getAllPackages/","/getAllPackages"})
    public ResponseEntity<List<Package>> getAllPackages() {
        List<Package> packages = packageService.getAllPackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/getActivePackages/{id}")
//    public ResponseEntity<Optional<UserPackage>> getAllActivePackages(@PathVariable Long id) {
////        Optional<Package> packages = packageService.getAllPackagesById(id);
//        Optional<UserPackage> packages = packageService.getAllPackagesById(id);
////        System.out.println(packages.toString());
//        return ResponseEntity.ok(packages);
//    }
    public ResponseEntity<List<UserPackageResponse>> getAllActivePackages(@PathVariable Long id) {
        List<UserPackage> activePackages = packageService.getActivePackagesByUserId(id);

        // Convert the list of UserPackage to a list of UserPackageResponse
        List<UserPackageResponse> response = activePackages.stream()
                .map(UserPackageResponse::new)
                .toList();

        return ResponseEntity.ok(response);
    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<UserPackage> updatePackage(@PathVariable Long id, @RequestBody PackageRequestDTO request) {
//        UserPackage updatedPackage = packageService.updatePackage(id, request);
//        return ResponseEntity.ok(updatedPackage);
//    }

    
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deletePackage(@PathVariable Long id) {
//        packageService.deletePackage(id);
//        return ResponseEntity.ok("Package deleted successfully");
//    }
//    @Autowired
//    private PackageService packageService;
//
//    // Register a new package
//    @PostMapping("/registerPackage")
//    public ResponseEntity<UserPackage> registerPackage(@RequestBody PackageRequestDTO request) {
//        UserPackage newPackage = packageService.addPackage(request);
//        return ResponseEntity.ok(newPackage);
//    }
//
//    // Update an existing package
//    @PutMapping("/updatePackage/{id}")
//    public ResponseEntity<UserPackage> updatePackage(@PathVariable Long id, @RequestBody PackageRequestDTO request) {
//        UserPackage updatedPackage = packageService.updatePackage(id, request);
//        return ResponseEntity.ok(updatedPackage);
//    }
//
//    // Get all packages
//    @GetMapping("/getAllPackages")
//    public ResponseEntity<List<UserPackage>> getAllPackages() {
//        List<UserPackage> packages = packageService.getAllPackages();
//        return ResponseEntity.ok(packages);
//    }
//
//    // Get all active packages
//    @GetMapping("/getAllActivePackages")
//    public ResponseEntity<List<UserPackage>> getAllActivePackages() {
//        List<UserPackage> activePackages = packageService.getAllActivePackages();
//        return ResponseEntity.ok(activePackages);
//    }
//
//    // Activate a package
//    @PostMapping("/activatePackage")
//    public ResponseEntity<String> activatePackage(@RequestParam Long userId, @RequestParam Long packageId) {
//        packageService.activatePackage(userId, packageId);
//        return ResponseEntity.ok("Package activated successfully");
//    }
//
//    // Deactivate a package
//    @PostMapping("/deactivatePackage")
//    public ResponseEntity<String> deactivatePackage(@RequestParam Long userId, @RequestParam Long packageId) {
//        packageService.deactivatePackage(userId, packageId);
//        return ResponseEntity.ok("Package deactivated successfully");
//    }
//
//    // Delete a package
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deletePackage(@PathVariable Long id) {
//        packageService.deletePackage(id);
//        return ResponseEntity.ok("Package deleted successfully");
//    }

//    @Autowired
//    private PackageService packageService;

//   ]
    @PostMapping("/registerPackage")
    public ResponseEntity<Package> registerPackage(@RequestBody PackageRequestDTO request) {
        Package newPackage = packageService.addPackage(request);
        return ResponseEntity.ok(newPackage);
    }


    @PutMapping("/updatePackage/{id}")
    public ResponseEntity<Optional<Package>> updatePackage(@PathVariable Long id, @RequestBody PackageRequestDTO request) {
        Optional<Package> updatedPackage = packageService.updatePackage(id, request);
        return ResponseEntity.ok(updatedPackage);
    }


//    @GetMapping("/getAllPackages")
//    public ResponseEntity<List<UserPackage>> getAllPackages() {
//        List<UserPackage> packages = packageService.getAllPackages();
//        return ResponseEntity.ok(packages);
//    }
//
//    @GetMapping("/getAllActivePackages")
//    public ResponseEntity<List<UserPackage>> getAllActivePackages() {
//        List<UserPackage> activePackages = packageService.getAllActivePackages();
//        return ResponseEntity.ok(activePackages);
//    }

    // Activate a package
    @PostMapping("/activatePackage/{packageId}")
    public ResponseEntity<String> activatePackage(@RequestParam Long userId, @PathVariable("packageId") Long packageId) {
        ActivePackageRequestDTO request = new ActivePackageRequestDTO();
        request.setUserId(userId);
        request.setPackageId(packageId);
        packageService.activatePackage(request);
        return ResponseEntity.ok("Package activated successfully");
    }

    @DeleteMapping("/deactivatePackage/{id}")
    public ResponseEntity<String> deactivatePackage(@PathVariable("id") Long packageId) {
//        ActivePackageRequestDTO request = new ActivePackageRequestDTO();
//        request.setUserId(userId);
//        request.setPackageId(packageId);
        packageService.deactivatePackage(packageId);
        return ResponseEntity.ok("Package deactivated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.ok("Package deleted successfully");
    }
}