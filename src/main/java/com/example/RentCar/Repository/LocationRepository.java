package com.example.RentCar.Repository;

import com.example.RentCar.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Float> {
}
