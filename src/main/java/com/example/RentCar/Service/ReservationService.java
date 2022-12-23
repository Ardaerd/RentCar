package com.example.RentCar.Service;

import com.example.RentCar.DTO.ReservationDTO;
import com.example.RentCar.Mapper.ReservationMapper;
import com.example.RentCar.Model.*;
import com.example.RentCar.Repository.CarRepository;
import com.example.RentCar.Repository.LocationRepository;
import com.example.RentCar.Repository.MemberRepository;
import com.example.RentCar.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ReservationMapper reservationMapper;

    @Transactional
    public ReservationDTO save(ReservationDTO dto) {
        Reservation reservation = reservationMapper.reservationDTOToEntity(dto);
        reservationRepository.save(reservation);

        return reservationMapper.reservationEntityToDTO(reservation);
    }

    @Transactional
    public ReservationDTO getReservationById(float id) {
        Reservation reservation = reservationRepository.findById(id).get();

        return reservationMapper.reservationEntityToDTO(reservation);
    }

    @Transactional
    public ReservationDTO makeReservation(String carBarcodeNum, int dayCount, Long memberId, int pickUpCode, int dropOffCode, List<Equipment> equipmentList, List<Service> serviceList) throws ParseException {
        double totalAmount = 0;

        Car car = carRepository.findCarByBarcode(carBarcodeNum);
        Member member = memberRepository.findById(memberId).get();
        Location pickUpLocation = locationRepository.findLocationByCode(pickUpCode);
        Location dropOffLocation = locationRepository.findLocationByCode(dropOffCode);

        int reservationNumInt = (int) (Math.random()*(99999999-10000000+1)+10000000);
        String reservationNumber = Integer.toString(reservationNumInt);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Date pickUpDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(formatter.format(date));

        Calendar c = Calendar.getInstance();
        c.setTime(pickUpDate);
        c.add(Calendar.DATE,dayCount);
        Date dropOffDate = c.getTime();

        totalAmount = car.getDailyPrice() * dayCount;

        for (Equipment equipment : equipmentList)
            totalAmount += equipment.getFixedPrice();

        for (Service service : serviceList)
            totalAmount += service.getFixedPrice();

        Reservation reservation = new Reservation(reservationNumber,car,pickUpDate,dropOffDate,dropOffLocation,pickUpLocation,dropOffDate,"Active",member,serviceList,equipmentList);
        reservationRepository.save(reservation);

        carRepository.updateStatus("Loaned",car.getId());

        ReservationDTO reservationDTO = reservationMapper.reservationEntityToDTO(reservation);

        reservationDTO.setAmount(totalAmount);
        reservationDTO.setPickUpLocation(pickUpLocation);

        return reservationDTO;
    }
}
