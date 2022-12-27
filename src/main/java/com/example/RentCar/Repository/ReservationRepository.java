package com.example.RentCar.Repository;

import com.example.RentCar.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Float> {

    @Query(value = "SELECT * FROM RESERVATION R WHERE R.CAR_ID  = :car",nativeQuery = true)
    List<Reservation> findReservationsByCarId(@Param("car") float carId);

    @Query(value = "SELECT * FROM RESERVATION R WHERE R.RESERVATION_NUMBER  = :reservationNumber",nativeQuery = true)
    Reservation findReservationByReservationNumber(@Param("reservationNumber") String reservationNumber);

    @Modifying
    @Query(value = "UPDATE RESERVATION SET STATUS = 'Completed', RETURN_DATE = CURRENT_DATE WHERE RESERVATION_NUMBER = :reservationNumber",nativeQuery = true)
    void updateStatusAndReturnDateByReservationNumber(@Param("reservationNumber") String reservationNumber);

}
