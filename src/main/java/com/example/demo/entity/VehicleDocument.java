package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle_document")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class VehicleDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;
    //rc document
    @Column(name = "rc_document_pic")
    private String rcDocumentpic;
    //insurance document
    @Column(name = "insurance_document_pic")
    private String insuranceDocumentPic;
    //pollution document
    @Column(name = "pollution_document_pic")
    private String pollutionDocumentPic;
    //vehcile revenue document
    @Column(name = "vehicle_revenue_document_pic")
    private String vehicleRevenueDocumentPic;
    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
    

}
