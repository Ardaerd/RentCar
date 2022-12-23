package com.example.RentCar.TestCar;

import com.example.RentCar.DTO.*;
import com.example.RentCar.Mapper.*;
import com.example.RentCar.Model.*;
import com.example.RentCar.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


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
    public void saveCar() {
        Car car1 = new Car("123",5463,4,"Tesla","Sport X","Auto",13,"Sport","available",130000L);
        Car car2 = new Car("423",2352,5,"Lambo","Urus","Auto",19,"Luxury","not-available",180000L);

        CarDTO carDTO1 = CarMapper.INSTANCE.carEntityToDTO(car1);
        CarDTO carDTO2 = CarMapper.INSTANCE.carEntityToDTO(car2);

        carService.save(carDTO1);
        carService.save(carDTO2);

        List<CarDTO> listofAvailableCars = carService.findAvailableCars("Sport","Auto");

        for (CarDTO carDTO : listofAvailableCars)
            System.out.println(carDTO.getBrand());

    }

    @Test
    @Commit
    public void saveMember() {
        saveCar();

        Member member1 = new Member("Arda","05415374277","arderd@gmail.com","Ozyegin Uni.",5);
        Member member2 = new Member("Uygar","0563212378","uygargn@gmail.com","Ozyegin Uni.",6);

        MemberDTO memberDTO1 = MemberMapper.INSTANCE.memberEntityToDTO(member1);
        MemberDTO memberDTO2 = MemberMapper.INSTANCE.memberEntityToDTO(member2);

        memberService.save(memberDTO1);
        memberService.save(memberDTO2);

    }

    @Test
    @Commit
    public void saveLocation() {
        saveMember();

        Location location1 = new Location(1,"İstanbul Airport");
        Location location2 = new Location(2,"Istanbul Sabiha Gökçen Airport");
        Location location3 = new Location(3,"Istanbul Kadıköy");
        Location location4 = new Location(4,"Izmir City Center");

        LocationDTO locationDTO1 = LocationMapper.INSTANCE.locationEntityToDTO(location1);
        LocationDTO locationDTO2 = LocationMapper.INSTANCE.locationEntityToDTO(location2);
        LocationDTO locationDTO3 = LocationMapper.INSTANCE.locationEntityToDTO(location3);
        LocationDTO locationDTO4 = LocationMapper.INSTANCE.locationEntityToDTO(location4);

        locationService.save(locationDTO1);
        locationService.save(locationDTO2);
        locationService.save(locationDTO3);
        locationService.save(locationDTO4);


    }


    @Test
    @Commit
    public void saveEquipment() {
        saveLocation();

        Equipment equipment1 = new Equipment(50.5,"Snow Tyres",1);
        Equipment equipment2 = new Equipment(20.5,"Child Seat",2);
        Equipment equipment3 = new Equipment(30.5,"Baby Seat",3);
        Equipment equipment4 = new Equipment(10.5,"Roof Box",4);
        Equipment equipment5 = new Equipment(60.5,"WIFI",5);
        Equipment equipment6 = new Equipment(10.5,"GPS",6);

        EquipmentDTO equipmentDTO1 = EquipmentMapper.INSTANCE.equipmentEntityToDTO(equipment1);
        EquipmentDTO equipmentDTO2 = EquipmentMapper.INSTANCE.equipmentEntityToDTO(equipment2);
        EquipmentDTO equipmentDTO3 = EquipmentMapper.INSTANCE.equipmentEntityToDTO(equipment3);
        EquipmentDTO equipmentDTO4 = EquipmentMapper.INSTANCE.equipmentEntityToDTO(equipment4);
        EquipmentDTO equipmentDTO5 = EquipmentMapper.INSTANCE.equipmentEntityToDTO(equipment5);
        EquipmentDTO equipmentDTO6 = EquipmentMapper.INSTANCE.equipmentEntityToDTO(equipment6);

        equipmentService.save(equipmentDTO1);
        equipmentService.save(equipmentDTO2);
        equipmentService.save(equipmentDTO3);
        equipmentService.save(equipmentDTO4);
        equipmentService.save(equipmentDTO5);
        equipmentService.save(equipmentDTO6);

    }

    @Test
    @Commit
    public void saveService() {
        saveEquipment();

        Service service1 = new Service(40,"Additional Driver",1);
        Service service2 = new Service(30,"Towing Service",2);
        Service service3 = new Service(10,"Roadside assistance",3);

        ServiceDTO serviceDTO1 = ServiceMapper.INSTANCE.serviceEntityToDTO(service1);
        ServiceDTO serviceDTO2 = ServiceMapper.INSTANCE.serviceEntityToDTO(service2);
        ServiceDTO serviceDTO3 = ServiceMapper.INSTANCE.serviceEntityToDTO(service3);

        serviceService.save(serviceDTO1);
        serviceService.save(serviceDTO2);
        serviceService.save(serviceDTO3);

    }

    @Test
    @Commit
    public void makeReservation() throws ParseException {
        saveService();

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
        Service service = new Service(40,"Additional Driver",4);
        ServiceDTO serviceDTO = ServiceMapper.INSTANCE.serviceEntityToDTO(service);

        serviceService.save(serviceDTO);

        ReservationDTO reservationDTO = reservationService.getReservationById(1);
        Reservation reservation = ReservationMapper.INSTANCE.reservationDTOToEntity(reservationDTO);

        serviceService.addAdditionalServiceToReservation(reservation.getReservationNumber(),4);
    }

}
