package com.example.RentCar.Mapper;

import com.example.RentCar.DTO.EquipmentDTO;
import com.example.RentCar.Model.Equipment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    EquipmentMapper INSTANCE = Mappers.getMapper(EquipmentMapper.class);

    EquipmentDTO equipmentEntityToDTO(Equipment equipment);

    @InheritInverseConfiguration
    @Mapping(target = "id")
    @Mapping(target = "fixedPrice")
    @Mapping(target = "name")
    Equipment equipmentDTOToEntity(EquipmentDTO dto);

}
