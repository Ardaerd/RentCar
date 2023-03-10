package com.example.RentCar.Repository;

import com.example.RentCar.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Float> {

    Service findServiceByCode(int code);

}
