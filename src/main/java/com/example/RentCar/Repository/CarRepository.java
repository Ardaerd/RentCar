package com.example.RentCar.Repository;

import com.example.RentCar.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Float> {

    @Query("SELECT C FROM Car C WHERE C.status = ?1")
    List<Car> findByStatus(String status);

}
