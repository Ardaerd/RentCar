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
    @Mapping(target = "id")
    @Mapping(target = "barcode")
    @Mapping(target = "licensePlateNumber")
    @Mapping(target = "passengerCapacity")
    @Mapping(target = "brand")
    @Mapping(target = "model")
    @Mapping(target = "transmissionType")
    @Mapping(target = "dailyPrice")
    @Mapping(target = "carType")
    @Mapping(target = "status")
    Car carDTOToEntity(CarDTO dto);

}
