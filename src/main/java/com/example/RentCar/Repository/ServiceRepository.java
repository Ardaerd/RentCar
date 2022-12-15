package com.example.RentCar.Repository;

import com.example.RentCar.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Float> {
}
