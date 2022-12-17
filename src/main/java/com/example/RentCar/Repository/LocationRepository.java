package com.example.RentCar.Repository;

import com.example.RentCar.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Float> {

    @Query(value = "SELECT * FROM LOCATION L WHERE L.CODE  = :code",nativeQuery = true)
    Location findLocationByCode(@Param("code") int code);

}
