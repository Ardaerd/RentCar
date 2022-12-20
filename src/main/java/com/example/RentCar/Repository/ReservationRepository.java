package com.example.RentCar.Repository;

import com.example.RentCar.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Float> {

    @Query(value = "SELECT * FROM RESERVATION R WHERE R.CAR_ID  = :car",nativeQuery = true)
    List<Reservation> findReservationsByCarId(@Param("car") float carId);


}
