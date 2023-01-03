package com.example.RentCar.Service;

import com.example.RentCar.DTO.ReservationDTO;
import com.example.RentCar.Mapper.ReservationMapper;
import com.example.RentCar.Model.*;
import com.example.RentCar.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ReservationMapper reservationMapper;


    @Transactional
    public ReservationDTO save(ReservationDTO dto) {
        Reservation reservation = reservationMapper.reservationDTOToEntity(dto);
        reservationRepository.save(reservation);

        return reservationMapper.reservationEntityToDTO(reservation);
    }

    @Transactional
    public void save(Reservation r) {

        reservationRepository.save(r);

    }


    @Transactional
    public boolean returnCar(String reservationNumber) {
        try {
            Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber);
            reservationRepository.updateStatusAndReturnDateByReservationNumber(reservationNumber);

            Car car = reservation.getCar();

            carRepository.updateStatus("Available", car.getId());

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Transactional
    public boolean cancelReservation(String reservationNumber) {

        try {
            Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber);
            reservationRepository.updateStatusByReservationNumber("Cancelled",reservationNumber);

            Car car = reservation.getCar();

            carRepository.updateStatus("Available",car.getId());

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }


    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationDTO> dtoList = new ArrayList<>();

        for (Reservation reservation : reservationList)
            dtoList.add(reservationMapper.reservationEntityToDTO(reservation));

        return dtoList;
    }

    public ReservationDTO getReservationById(float id) {
        Reservation reservation = reservationRepository.findById(id).get();

        return reservationMapper.reservationEntityToDTO(reservation);
    }

    @Transactional
    public ReservationDTO makeReservation(String carBarcodeNum, int dayCount, Long memberId, int pickUpCode, int dropOffCode, List<Equipment> equipmentList, List<Service> serviceList) throws ParseException {
        double totalAmount = 0;

        Car car = carRepository.findCarByBarcode(carBarcodeNum);

        if (car.getStatus().equals("Available")) {
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
        } else {
            return null;
        }
    }

    @Transactional
    public void initalize() throws ParseException {
        List<Equipment> equipmentList = new ArrayList<>();
        List<Service> serviceList = new ArrayList<>();

        Car car1 = new Car("123",
                5463,
                4,
                "Tesla",
                "Sport X",
                "Auto",
                13,
                "Sport",
                "Available",
                130000L);

        Car car2 = new Car("423",
                2352,
                5,
                "Lambo",
                "Urus",
                "Auto",
                19,
                "Luxury",
                "Not-Available",
                180000L);

        Car car3 = new Car("1323",
                5235,
                6,
                "Mercedes",
                "G20",
                "Auto",
                19,
                "Luxury",
                "Available",
                10000L);

        Equipment equipment1 = new Equipment(50.5,"Snow Tyres",1);
        Equipment equipment2 = new Equipment(20.5,"Child Seat",2);
        Equipment equipment3 = new Equipment(30.5,"Baby Seat",3);
        Equipment equipment4 = new Equipment(10.5,"Roof Box",4);
        Equipment equipment5 = new Equipment(60.5,"WIFI",5);
        Equipment equipment6 = new Equipment(10.5,"GPS",6);

        Location location1 = new Location(1,"İstanbul Airport");
        Location location2 = new Location(2,"Istanbul Sabiha Gökçen Airport");
        Location location3 = new Location(3,"Istanbul Kadıköy");
        Location location4 = new Location(4,"Izmir City Center");

        Member member1 = new Member("Arda","05415374277","arderd@gmail.com","Ozyegin Uni.",5);
        Member member2 = new Member("Uygar","0563212378","uygargn@gmail.com","Ozyegin Uni.",6);

        Service service1 = new Service(40,"Additional Driver",1);
        Service service2 = new Service(30,"Towing Service",2);
        Service service3 = new Service(10,"Roadside assistance",3);

        equipmentList.add(equipment1);
        equipmentList.add(equipment2);
        equipmentList.add(equipment3);
        equipmentList.add(equipment4);
        equipmentList.add(equipment5);
        equipmentList.add(equipment6);

        serviceList.add(service1);
        serviceList.add(service2);
        serviceList.add(service3);


        carRepository.saveAll(List.of(car1,car2,car3));
        locationRepository.saveAll(List.of(location1,location2,location3,location4));
        memberRepository.saveAll(List.of(member1,member2));
        equipmentRepository.saveAll(List.of(equipment1,equipment2));
        serviceRepository.saveAll(serviceList);

        makeReservation("1323",5,1L,1,2,equipmentList,serviceList);
    }
}
