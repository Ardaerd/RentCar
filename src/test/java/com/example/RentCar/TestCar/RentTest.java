package com.example.RentCar.TestCar;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.DTO.EquipmentDTO;
import com.example.RentCar.DTO.LocationDTO;
import com.example.RentCar.DTO.MemberDTO;
import com.example.RentCar.Mapper.CarMapper;
import com.example.RentCar.Mapper.EquipmentMapper;
import com.example.RentCar.Mapper.LocationMapper;
import com.example.RentCar.Mapper.MemberMapper;
import com.example.RentCar.Model.Car;
import com.example.RentCar.Model.Equipment;
import com.example.RentCar.Model.Location;
import com.example.RentCar.Model.Member;
import com.example.RentCar.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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
        Location location2 = new Location(2,"İstanbul Sabiha Gökçen Airport");
        Location location3 = new Location(3,"İstanbul Kadıköy");
        Location location4 = new Location(4,"İzmir City Center");

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

        Equipment equipment1 = new Equipment(50.5,"Snow Tyres");
        Equipment equipment2 = new Equipment(20.5,"Child Seat");
        Equipment equipment3 = new Equipment(30.5,"Baby Seat");
        Equipment equipment4 = new Equipment(10.5,"Roof Box");
        Equipment equipment5 = new Equipment(60.5,"WIFI");
        Equipment equipment6 = new Equipment(10.5,"GPS");

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
    public void makeReservation() {
        saveLocation();

        //reservationService.makeReservation("123",5,1,1,2,)

    }


}
