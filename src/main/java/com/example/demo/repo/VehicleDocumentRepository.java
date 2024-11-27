package com.example.demo.repo;

import com.example.demo.entity.VehicleDocument;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDocumentRepository extends JpaRepository<VehicleDocument, Long> {
}
