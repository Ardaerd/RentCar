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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Transactional
    public void saveList(List<Car> cars) {

        carRepository.saveAll(cars);

    }

    @Transactional
    public boolean deleteCar(String carBarcodeNum) {

        try {
            Car car = carRepository.findCarByBarcode(carBarcodeNum);
            List<Reservation> reservation = reservationRepository.findReservationsByCarId(car.getId());

            if (car.getStatus().equals("Available") && reservation.size() < 1) {

                carRepository.deleteCarByBarcode(carBarcodeNum);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> dtoList = new ArrayList<>();

        for (Car car : cars)
            dtoList.add(carMapper.carEntityToDTO(car));

        return dtoList;
    }

    public List<CarDTO> findAvailableCars(String carType, String transmissionType) {
        List<Car> listOfCars = carRepository.findDesiredCars("Available",carType,transmissionType);
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

        for (RentedCarDTO dto : dtoList) {
            Reservation reservation = reservationRepository.findReservationByReservationNumber(dto.getReservationNumber());

            Date pickUp = reservation.getPickUpDate();
            Date dropOff = reservation.getDropOffDate();

            long diffInMillies = Math.abs(pickUp.getTime() - dropOff.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            dto.setReservationDayCount((int) diff);
        }

        return dtoList;
    }

}
