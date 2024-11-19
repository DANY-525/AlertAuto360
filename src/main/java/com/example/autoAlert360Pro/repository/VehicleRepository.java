package com.example.autoAlert360Pro.repository;
import com.example.autoAlert360Pro.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Custom query to find vehicles by user ID
    List<Vehicle> findByUserId(Long userId);

    // Custom query to find a vehicle by its license plate
    Vehicle findByLicensePlate(String licensePlate);
}