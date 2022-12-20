package com.example.RentCar.Service;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.DTO.RentedCarDTO;
import com.example.RentCar.Mapper.CarMapper;
import com.example.RentCar.Mapper.MemberMapper;
import com.example.RentCar.Mapper.RentedCarMapper;
import com.example.RentCar.Mapper.ReservationMapper;
import com.example.RentCar.Model.Car;
import com.example.RentCar.Model.Member;
import com.example.RentCar.Model.Reservation;
import com.example.RentCar.Repository.CarRepository;
import com.example.RentCar.Repository.MemberRepository;
import com.example.RentCar.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RentedCarMapper rentedCarMapper;

    @Autowired
    private CarMapper carMapper;

    public CarDTO save(CarDTO dto) {
        Car car = carMapper.carDTOToEntity(dto);
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

    public List<RentedCarDTO> getAllRentedCars() {
        List<RentedCarDTO> dtoList = new ArrayList<>();
        List<Car> loanedAndReservedCars = carRepository.findCarsByStatus();

        for (Car car : loanedAndReservedCars)
            System.out.println(car.getId());

        for (Car car : loanedAndReservedCars) {
            for (Reservation reservation : reservationRepository.findReservationsByCarId(car.getId())) {
                Member member = memberRepository.findById(reservation.getMember().getId()).get();
                dtoList.add(rentedCarMapper.entitiesToDTO(reservation,car,member));
            }
        }

        return dtoList;
    }

}
