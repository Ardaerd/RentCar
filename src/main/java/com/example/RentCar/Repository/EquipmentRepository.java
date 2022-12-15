package com.example.RentCar.Repository;

import com.example.RentCar.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment,Float> {
}
