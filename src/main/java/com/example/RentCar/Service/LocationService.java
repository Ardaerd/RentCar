package com.example.RentCar.Service;

import com.example.RentCar.DTO.LocationDTO;
import com.example.RentCar.Mapper.LocationMapper;
import com.example.RentCar.Model.Location;
import com.example.RentCar.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    public LocationDTO save(LocationDTO dto) {
        Location location = locationMapper.locationDTOToEntity(dto);
        locationRepository.save(location);

        return locationMapper.locationEntityToDTO(location);
    }

}
