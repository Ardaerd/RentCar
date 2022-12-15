package com.example.RentCar.Repository;

import com.example.RentCar.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Float> {
}
