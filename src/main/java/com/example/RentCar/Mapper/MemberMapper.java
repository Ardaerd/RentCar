package com.example.RentCar.Mapper;

import com.example.RentCar.DTO.MemberDTO;
import com.example.RentCar.Model.Member;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDTO memberEntityToDTO(Member member);

    @InheritInverseConfiguration
    @Mapping(target = "id")
    @Mapping(target = "name")
    @Mapping(target = "phoneNumber")
    @Mapping(target = "email")
    @Mapping(target = "address")
    @Mapping(target = "drivingLicenseNumber")
    Member memberDTOToEntity(MemberDTO dto);

}
