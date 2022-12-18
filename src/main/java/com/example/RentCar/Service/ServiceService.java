package com.example.RentCar.Service;

import com.example.RentCar.DTO.ServiceDTO;
import com.example.RentCar.Mapper.ServiceMapper;
import com.example.RentCar.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    public ServiceDTO save(ServiceDTO dto) {

        com.example.RentCar.Model.Service service = (com.example.RentCar.Model.Service) serviceMapper.serviceDTOToEntity(dto);
        serviceRepository.save(service);

        return serviceMapper.serviceEntityToDTO(service);
    }

    public List<ServiceDTO> getAllServiceList() {
        List<com.example.RentCar.Model.Service> listOfService = serviceRepository.findAll();
        List<ServiceDTO> dtoList = new ArrayList<>();

        for (com.example.RentCar.Model.Service service : listOfService)
            dtoList.add(serviceMapper.serviceEntityToDTO(service));

        return dtoList;

    }

}
