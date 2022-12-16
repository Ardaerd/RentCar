package com.example.RentCar.Service;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.Mapper.*;
import com.example.RentCar.Model.Car;
import com.example.RentCar.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    public CarDTO save(CarDTO dto) {
        Car car = carMapper.carDTOToEntity(dto);
        System.out.println(car.getCarType());
        carRepository.save(car);

        return carMapper.carEntityToDTO(car);
    }

    public List<CarDTO> findAvailableCars(String carType, String transmissionType) {
        List<Car> listOfCars = carRepository.findDesiredCars("available",carType,transmissionType);
        List<CarDTO> dtoList = new ArrayList<>();

        for (Car car : listOfCars)
            dtoList.add(carMapper.carEntityToDTO(car));

        return dtoList;
    }

}
