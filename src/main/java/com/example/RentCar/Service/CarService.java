package com.example.RentCar.Service;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.Mapper.CarMapper;
import com.example.RentCar.Model.Car;
import com.example.RentCar.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public CarDTO save(CarDTO dto) {
        Car car = carMapper.carDTOToEntity(dto);
        System.out.println(car.getCarTypes());
        carRepository.save(car);

        return carMapper.carEntityToDTO(car);
    }

    public List<CarDTO> findAvailableCars() {
        List<Car> listOfCars = carRepository.findByStatus("available");
        List<CarDTO> dtoList = new ArrayList<>();

        for (Car car : listOfCars)
            dtoList.add(carMapper.carEntityToDTO(car));

        return dtoList;
    }

}
