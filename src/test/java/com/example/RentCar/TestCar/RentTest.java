package com.example.RentCar.TestCar;

import com.example.RentCar.DTO.*;
import com.example.RentCar.Mapper.EquipmentMapper;
import com.example.RentCar.Mapper.ReservationMapper;
import com.example.RentCar.Mapper.ServiceMapper;
import com.example.RentCar.Model.Equipment;
import com.example.RentCar.Model.Reservation;
import com.example.RentCar.Model.Service;
import com.example.RentCar.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class RentTest {

    @Autowired
    CarService carService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

    @Autowired
    LocationService locationService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    EquipmentMapper equipmentMapper;

    @Autowired
    ServiceMapper serviceMapper;

    @Test
    @Commit
    public void testFindAvailableCars() {

        List<CarDTO> listofAvailableCars = carService.findAvailableCars("Sport","Auto");

        for (CarDTO carDTO : listofAvailableCars)
            System.out.println(carDTO.getBrand());

    }

    @Test
    @Commit
    public void makeReservation() throws ParseException {
        testFindAvailableCars();
        List<EquipmentDTO> equipmentDTOList = equipmentService.getAllEquipmentList();
        List<ServiceDTO> serviceDTOList = serviceService.getAllServiceList();

        List<Equipment> equipmentList = new ArrayList<>();
        List<Service> serviceList = new ArrayList<>();

        for (EquipmentDTO equipment : equipmentDTOList)
            equipmentList.add(equipmentMapper.equipmentDTOToEntity(equipment));

        for (ServiceDTO service : serviceDTOList)
            serviceList.add(serviceMapper.serviceDTOToEntity(service));

        ReservationDTO reservationDTO = reservationService.makeReservation("123",5,1L,1,2,equipmentList,serviceList);

        System.out.println(reservationDTO.getReservationNumber());
        System.out.println(reservationDTO.getPickUpDate());
        System.out.println(reservationDTO.getDropOffDate());
        System.out.println(reservationDTO.getDropOffLocation().getCode() + "- " + reservationDTO.getDropOffLocation().getAddress());
        System.out.println(reservationDTO.getAmount());
    }

    @Test
    @Commit
    public void testGetAllRentedCar() throws ParseException {
        makeReservation();
        System.out.println("Rented Cars: ");

        List<RentedCarDTO> dtos= carService.getAllRentedCars();

        for (RentedCarDTO dto : dtos) {
            System.out.println(dto.getBarcode());
            System.out.println(dto.getCarType());
            System.out.println(dto.getModel());
            System.out.println(dto.getMemberName());
            System.out.println(dto.getTransmissionType());
            System.out.println(dto.getReservationDayCount()); // this is not working
        }
    }

    @Test
    @Commit
    public void testAddAdditionalServices() throws ParseException {
        makeReservation();
        Service service = new Service(50,"Protection",4);
        ServiceDTO serviceDTO = ServiceMapper.INSTANCE.serviceEntityToDTO(service);

        serviceService.save(serviceDTO);

        ReservationDTO reservationDTO = reservationService.getReservationById(1);
        Reservation reservation = ReservationMapper.INSTANCE.reservationDTOToEntity(reservationDTO);

        serviceService.addAdditionalServiceToReservation(reservation.getReservationNumber(),service.getCode());
    }

    @Test
    @Commit
    public void testAddAdditionalEquipment() throws ParseException {
        testAddAdditionalServices();
        Equipment equipment = new Equipment(15,"Turbo",7);
        EquipmentDTO equipmentDTO = EquipmentMapper.INSTANCE.equipmentEntityToDTO(equipment);

        equipmentService.save(equipmentDTO);

        ReservationDTO reservationDTO = reservationService.getReservationById(1);
        Reservation reservation = ReservationMapper.INSTANCE.reservationDTOToEntity(reservationDTO);

        equipmentService.addAdditionalEquipmentToReservation(reservation.getReservationNumber(),equipment.getCode());

    }

    @Test
    @Commit
    public void testReturnCar() throws ParseException {
        testAddAdditionalEquipment();
        boolean isReturn = reservationService.returnCar("12345678");
        System.out.println(isReturn);
    }

    @Test
    @Commit
    public void testDeleteCar() throws ParseException {
        testReturnCar();
        carService.deleteCar("423");
    }

    @Test
    @Commit
    public void testCancelReservation() {
        boolean isCancelled = reservationService.cancelReservation("12345678");
        assertTrue(isCancelled);
    }

}
