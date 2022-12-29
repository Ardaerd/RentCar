package com.example.RentCar.Service;

import com.example.RentCar.DTO.EquipmentDTO;
import com.example.RentCar.Mapper.EquipmentMapper;
import com.example.RentCar.Model.Equipment;
import com.example.RentCar.Model.Reservation;
import com.example.RentCar.Repository.EquipmentRepository;
import com.example.RentCar.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Transactional
    public EquipmentDTO save(EquipmentDTO dto) {
        Equipment equipment = equipmentMapper.equipmentDTOToEntity(dto);
        equipmentRepository.save(equipment);

        return equipmentMapper.equipmentEntityToDTO(equipment);
    }

    @Transactional
    public void saveAll(List<Equipment> equipmentList) {
        equipmentRepository.saveAll(equipmentList);

    }

    @Transactional
    public EquipmentDTO getEquipmentById(Float id) {
        Equipment equipment = equipmentRepository.findById(id).get();
        EquipmentDTO equipmentDTO = equipmentMapper.equipmentEntityToDTO(equipment);

        return equipmentDTO;
    }

    public List<EquipmentDTO> getAllEquipmentList() {
        List<Equipment> lisOfEquipment = equipmentRepository.findAll();
        List<EquipmentDTO> dtoList = new ArrayList<>();

        for (Equipment equipment : lisOfEquipment)
            dtoList.add(equipmentMapper.equipmentEntityToDTO(equipment));

        return dtoList;
    }

    @Transactional
    public List<Equipment> getEquitments() {
        List<Equipment> lisOfEquipment = equipmentRepository.findAll();

        return lisOfEquipment;
    }


    @Transactional
    public Boolean addAdditionalEquipmentToReservation(String reservationNum,int equipmentCode) {
        try {

            Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNum);
            Equipment equipment = equipmentRepository.findEquipmentByCode(equipmentCode);

            List<Equipment> equipmentList = reservation.getEquipments();

            System.out.println("Id: " + equipment.getId());

            equipmentList.add(equipment);
            reservation.setEquipments(equipmentList);

            reservationRepository.save(reservation);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
