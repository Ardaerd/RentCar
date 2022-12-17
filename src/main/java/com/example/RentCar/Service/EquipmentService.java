package com.example.RentCar.Service;

import com.example.RentCar.DTO.EquipmentDTO;
import com.example.RentCar.Mapper.EquipmentMapper;
import com.example.RentCar.Model.Equipment;
import com.example.RentCar.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentMapper equipmentMapper;

    public EquipmentDTO save(EquipmentDTO dto) {
        Equipment equipment = equipmentMapper.equipmentDTOToEntity(dto);
        equipmentRepository.save(equipment);

        return equipmentMapper.equipmentEntityToDTO(equipment);
    }

}
