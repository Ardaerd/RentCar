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

//    @InheritInverseConfiguration
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "name")
//    @Mapping(target = "phoneNumber",ignore = true)
//    @Mapping(target = "email",ignore = true)
//    @Mapping(target = "address",ignore = true)
//    @Mapping(target = "drivingLicenseNumber",ignore = true)
//    Member rentedCarDTOToMemberEntity(RentedCarDTO dto);
//
//    @InheritInverseConfiguration
//    @Mapping(target = "id",ignore = true)
//    @Mapping(target = "barcode")
//    @Mapping(target = "licensePlateNumber",ignore = true)
//    @Mapping(target = "passengerCapacity",ignore = true)
//    @Mapping(target = "brand")
//    @Mapping(target = "model")
//    @Mapping(target = "transmissionType",ignore = true)
//    @Mapping(target = "dailyPrice",ignore = true)
//    @Mapping(target = "carType")
//    @Mapping(target = "status",ignore = true)
//    @Mapping(target = "mileage",ignore = true)
//    Car rentedCarDTOToCarEntity(RentedCarDTO dto);
//
//    @InheritInverseConfiguration
//    @Mapping(target = "id",ignore = true)
//    @Mapping(target = "reservationNumber")
//    @Mapping(target = "car")
//    @Mapping(target = "creationDate",ignore = true)
//    @Mapping(target = "pickUpDate",ignore = true)
//    @Mapping(target = "dropOffDate")
//    @Mapping(target = "dropOffLocation")
//    @Mapping(target = "pickUpLocation",ignore = true)
//    @Mapping(target = "returnDate",ignore = true)
//    @Mapping(target = "status",ignore = true)
//    @Mapping(target = "member",ignore = true)
//    Reservation rentedCarDTOToReservationEntity(RentedCarDTO dto);
}
