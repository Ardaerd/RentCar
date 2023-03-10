package com.example.RentCar.Mapper;

import com.example.RentCar.DTO.ServiceDTO;
import com.example.RentCar.Model.Service;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    ServiceDTO serviceEntityToDTO(Service service);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "fixedPrice")
    @Mapping(target = "name")
    @Mapping(target = "code")
    Service serviceDTOToEntity(ServiceDTO dto);
}
