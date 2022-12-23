package com.example.RentCar.Service;

import com.example.RentCar.DTO.ServiceDTO;
import com.example.RentCar.Mapper.ReservationMapper;
import com.example.RentCar.Mapper.ServiceMapper;
import com.example.RentCar.Model.Reservation;
import com.example.RentCar.Repository.ReservationRepository;
import com.example.RentCar.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    @Transactional
    public ServiceDTO save(ServiceDTO dto) {

        com.example.RentCar.Model.Service service = (com.example.RentCar.Model.Service) serviceMapper.serviceDTOToEntity(dto);
        serviceRepository.save(service);

        return serviceMapper.serviceEntityToDTO(service);
    }

    @Transactional
    public List<ServiceDTO> getAllServiceList() {
        List<com.example.RentCar.Model.Service> listOfService = serviceRepository.findAll();
        List<ServiceDTO> dtoList = new ArrayList<>();

        for (com.example.RentCar.Model.Service service : listOfService)
            dtoList.add(serviceMapper.serviceEntityToDTO(service));

        return dtoList;

    }

    @Transactional
    public boolean addAdditionalServiceToReservation(String reservationNumber,int code) {
        try {
            Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber);
            com.example.RentCar.Model.Service service = serviceRepository.findServiceByCode(code);

            List<com.example.RentCar.Model.Service> services = reservation.getServices();

            services.add(service);

            reservation.setServices(services);

            reservationRepository.save(reservation);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
