package com.example.RentCar.TestCar;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.Mapper.CarMapper;
import com.example.RentCar.Model.Car;
import com.example.RentCar.Service.RentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;


@SpringBootTest
public class TestCar {

    @Autowired
    RentService rentService;

    @Test
    @Commit
    public void saveCar() {
        Car car1 = new Car(123,5463,4,"Tesla","Sport X","Auto",13,"Sport","available");
        Car car2 = new Car(423,2352,5,"Lambo","Urus","Auto",19,"Luxury","not-available");

        CarDTO carDTO1 = CarMapper.INSTANCE.carEntityToDTO(car1);
        CarDTO carDTO2 = CarMapper.INSTANCE.carEntityToDTO(car2);

        rentService.save(carDTO1);
        rentService.save(carDTO2);

        List<CarDTO> listofAvailableCars = rentService.findAvailableCars("Sport","Auto");

        for (CarDTO carDTO : listofAvailableCars)
            System.out.println(carDTO.getBrand());

    }



}
