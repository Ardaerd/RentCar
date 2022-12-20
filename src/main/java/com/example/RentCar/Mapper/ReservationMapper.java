package com.example.RentCar.Mapper;

import com.example.RentCar.DTO.ReservationDTO;
import com.example.RentCar.Model.Reservation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(target = "amount",ignore = true)
    ReservationDTO reservationEntityToDTO(Reservation reservation);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "reservationNumber")
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "creationDate",ignore = true)
    @Mapping(target = "pickUpDate")
    @Mapping(target = "dropOffDate")
    @Mapping(target = "pickUpLocation")
    @Mapping(target = "dropOffLocation")
    @Mapping(target = "returnDate",ignore = true)
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "member",ignore = true)
    Reservation reservationDTOToEntity(ReservationDTO dto);
}
