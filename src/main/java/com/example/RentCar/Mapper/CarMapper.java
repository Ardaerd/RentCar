package com.example.RentCar.Mapper;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.Model.Car;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);


    CarDTO carEntityToDTO(Car car);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "barcode", ignore = true)
    @Mapping(target = "licensePlateNumber", ignore = true)
    @Mapping(target = "passengerCapacity", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "transmissionType", ignore = true)
    @Mapping(target = "dailyPrice", ignore = true)
    @Mapping(target = "carTypes", ignore = true)
    @Mapping(target = "status", ignore = true)
    Car carDTOToEntity(CarDTO dto);

}
