package com.example.RentCar.Repository;

import com.example.RentCar.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Float> {

    @Query(value = "SELECT * FROM CAR C WHERE C.STATUS  = :status AND C.CAR_TYPE = :carType AND C.TRANSMISSION_TYPE = :transmissionType",nativeQuery = true)
    List<Car> findDesiredCars(@Param("status") String status, @Param("carType") String carType, @Param("transmissionType") String transmissionType);

}
