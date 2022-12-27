//package com.example.RentCar.Config;
//
//import com.example.RentCar.Model.*;
//import com.example.RentCar.Repository.*;
//import com.example.RentCar.Service.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@Transactional
//public class RentCarConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(
//            CarService carService,
//            EquipmentService equipmentService,
//            ServiceService serviceService,
//            MemberService memberService,
//            ReservationService reservationService,
//            LocationRepository locationRepository
//    ) {
//        return args -> {
//            List<Equipment> equipmentList = new ArrayList<>();
//            List<Service> serviceList = new ArrayList<>();
//
//            Car car1 = new Car("123",
//                    5463,
//                    4,
//                    "Tesla esma",
//                    "Sport X",
//                    "Auto",
//                    13,
//                    "Sport",
//                    "Available",
//                    130000L);
//
//            Car car2 = new Car("423",
//                    2352,
//                    5,
//                    "Lambo",
//                    "Urus",
//                    "Auto",
//                    19,
//                    "Luxury",
//                    "Not-Available",
//                    180000L);
//
//            Car car3 = new Car("1323",
//                    5235,
//                    6,
//                    "Mercedes",
//                    "G20",
//                    "Auto",
//                    19,
//                    "Luxury",
//                    "Available",
//                    10000L);
//
//            Equipment equipment1 = new Equipment(50.5,"Snow Tyres",1);
//            Equipment equipment2 = new Equipment(20.5,"Child Seat",2);
//            Equipment equipment3 = new Equipment(30.5,"Baby Seat",3);
//            Equipment equipment4 = new Equipment(10.5,"Roof Box",4);
//            Equipment equipment5 = new Equipment(60.5,"WIFI",5);
//            Equipment equipment6 = new Equipment(10.5,"GPS",6);
//
//            Location location1 = new Location(1,"İstanbul Airport");
//            Location location2 = new Location(2,"Istanbul Sabiha Gökçen Airport");
//            Location location3 = new Location(3,"Istanbul Kadıköy");
//            Location location4 = new Location(4,"Izmir City Center");
//
//            Member member1 = new Member("Arda","05415374277","arderd@gmail.com","Ozyegin Uni.",5);
//            Member member2 = new Member("Uygar","0563212378","uygargn@gmail.com","Ozyegin Uni.",6);
//
//            Service service1 = new Service(40,"Additional Driver",1);
//            Service service2 = new Service(30,"Towing Service",2);
//            Service service3 = new Service(10,"Roadside assistance",3);
//
//            equipmentList.add(equipment1);
//            equipmentList.add(equipment2);
//            equipmentList.add(equipment3);
//            equipmentList.add(equipment4);
//            equipmentList.add(equipment5);
//            equipmentList.add(equipment6);
//
//            serviceList.add(service1);
//            serviceList.add(service2);
//            serviceList.add(service3);
//
//
//            carService.saveList(List.of(car1,car2,car3));
//            locationRepository.saveAll(List.of(location1,location2,location3,location4));
//            memberService.save(List.of(member1,member2));
//            equipmentService.saveAll(List.of(equipment1,equipment2));
//            serviceService.saveList(serviceList);
//
//            Reservation reservation = new Reservation("12345678",car1,new java.sql.Date(System.currentTimeMillis()),new java.sql.Date(System.currentTimeMillis()),new java.sql.Date(System.currentTimeMillis()),location1,location2,new java.sql.Date(System.currentTimeMillis()),"Available",member1);
//
//            reservationService.save(reservation);
//
////            List<Equipment> equipments = equipmentRepository.findAll();
////            List<Service> services = serviceRepository.findAll();
////
////            reservationService.makeReservation("123",4, 1L,1,2,equipments,services);
//        };
//    }
//}
