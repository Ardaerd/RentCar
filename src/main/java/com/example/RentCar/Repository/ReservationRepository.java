package com.example.RentCar.Repository;

import com.example.RentCar.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Float> {
}
