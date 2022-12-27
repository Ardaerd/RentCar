package com.example.RentCar.Controller;

import com.example.RentCar.DTO.EquipmentDTO;
import com.example.RentCar.DTO.ServiceDTO;
import com.example.RentCar.Service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Equipments")
@CrossOrigin
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping(value = "/getEquipment")
    @Operation(summary = "Find all equipments",description = "Get all equipments from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ServiceDTO.class))),
            @ApiResponse(responseCode = "404", description = "There is no equipment") })
    public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
        List<EquipmentDTO> dtoList = equipmentService.getAllEquipmentList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping(value = "/addEquipmentToReservation/{reservationNum}/{equipmentCode}")
    @Operation(summary = "Add equipment to the reservation", description = "Add equipment to the reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = EquipmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "505", description = "exception thrown")})
    public ResponseEntity<Boolean> addAdditionalServicesToReservation(@PathVariable("reservationNum") String reservationNumber, @PathVariable("equipmentCode") int equipmentCode) {
        Boolean isAdded = equipmentService.addAdditionalEquipmentToReservation(reservationNumber,equipmentCode);
        return ResponseEntity.status(HttpStatus.OK).body(isAdded);
    }

}
