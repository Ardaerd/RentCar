package com.example.RentCar.Config;

import com.example.RentCar.Service.ReservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Transactional
public class RentCarConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            ReservationService reservationService
    ) {
        return args -> {
            reservationService.initalize();
        };
    }
}
