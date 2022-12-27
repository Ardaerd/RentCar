package com.example.RentCar.Controller;

import com.example.RentCar.DTO.RentedCarDTO;
import com.example.RentCar.DTO.ReservationDTO;
import com.example.RentCar.Model.Equipment;
import com.example.RentCar.Model.Service;
import com.example.RentCar.Service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/Reservations")
@CrossOrigin
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/getReservations")
    @Operation(summary = "Find all reservations", description = "getting reservations data from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "404", description = "There is no reservation") })
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        List<ReservationDTO> dtoList = reservationService.getAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping(value = "/makeReservation/{carBarcode}/{dayCount}/{memberId}/{pickUpCode}/{dropOffCode}")
    @Operation(summary = "Make Reservation", description = "Make reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RentedCarDTO.class))),
            @ApiResponse(responseCode = "404", description = "No reservation can made") })
    public ResponseEntity<ReservationDTO> makeReservation(@PathVariable("carBarcode") String carBarcodeNum,@PathVariable("dayCount") int dayCount,@PathVariable("memberId") Long memberId,@PathVariable("pickUpCode") int pickUpCode,@PathVariable("dropOffCode") int dropOffCode,@RequestBody List<Equipment> equipments,@RequestBody List<Service> services) throws ParseException {
        ReservationDTO dto = reservationService.makeReservation(carBarcodeNum, dayCount, memberId, pickUpCode, dropOffCode, equipments, services);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "/returnCar/{reservationNumber}")
    @Operation(summary = "Returning the car",description = "Updating car status Available and reservation status Completed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Exception is thrown") })
    public ResponseEntity<Boolean> returnCar(@PathVariable("reservationNumber") String reservationNumber) {
        Boolean isCarReturned = reservationService.returnCar(reservationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(isCarReturned);
    }
}
