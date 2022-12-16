package com.example.RentCar.Mapper;

import com.example.RentCar.DTO.LocationDTO;
import com.example.RentCar.Model.Location;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDTO locationEntityToDTO(Location location);

    @InheritInverseConfiguration
    @Mapping(target = "id")
    @Mapping(target = "code")
    @Mapping(target = "address")
    Location locationDTOToEntity(LocationDTO dto);
}
