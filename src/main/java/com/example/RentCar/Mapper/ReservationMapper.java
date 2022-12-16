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

    ReservationDTO reservationEntityToDTO(Reservation reservation);

    @InheritInverseConfiguration
    @Mapping(target = "id")
    @Mapping(target = "reservationNumber")
    @Mapping(target = "creationDate")
    @Mapping(target = "pickUpDate")
    @Mapping(target = "dropOffDate")
    @Mapping(target = "dropOffLocation")
    @Mapping(target = "returnDate")
    @Mapping(target = "statu")
    Reservation reservationDTOToEntity(ReservationDTO dto);
}
