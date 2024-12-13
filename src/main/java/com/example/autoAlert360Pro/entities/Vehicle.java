package com.example.autoAlert360Pro.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicles", schema = "auto_alert360pro")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "soat_expiration_date", nullable = false)
    private java.time.LocalDate soatExpirationDate;

    @Column(name = "tecno_expiration_date", nullable = false)
    private java.time.LocalDate tecnoExpirationDate;


    @Column(name = "user_id", nullable = false)
    private Integer userId;
}