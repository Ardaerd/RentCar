package com.example.RentCar.Mapper;

import com.example.RentCar.DTO.RentedCarDTO;
import com.example.RentCar.Model.Car;
import com.example.RentCar.Model.Member;
import com.example.RentCar.Model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentedCarMapper {

    RentedCarMapper INSTANCE = Mappers.getMapper(RentedCarMapper.class);

    @Mapping(source = "member.name", target = "memberName")
    @Mapping(target = "reservationDayCount",ignore = true)
    RentedCarDTO entitiesToDTO(Reservation reservation, Car car, Member member);
}
