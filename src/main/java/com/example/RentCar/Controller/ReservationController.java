package com.example.RentCar.Controller;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.DTO.ReservationDTO;
import com.example.RentCar.Model.Equipment;
import com.example.RentCar.Model.Service;
import com.example.RentCar.Service.CarService;
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
    @Autowired
    CarService carService;

    @GetMapping(value = "/getReservations")
    @Operation(summary = "Find all reservations", description = "getting reservations data from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "404", description = "There is no reservation") })
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        List<ReservationDTO> dtoList = reservationService.getAllReservations();

        if(dtoList == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoList);

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping(value = "/makeReservation/{carBarcode}/{dayCount}/{memberId}/{pickUpCode}/{dropOffCode}")
    @Operation(summary = "Make Reservation", description = "Make reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "206", description = "Selected Car is not Available") })
    public ResponseEntity<ReservationDTO> makeReservation(@PathVariable("carBarcode") String carBarcodeNum,@PathVariable("dayCount") int dayCount,@PathVariable("memberId") Long memberId,@PathVariable("pickUpCode") int pickUpCode,@PathVariable("dropOffCode") int dropOffCode,@RequestParam(value="equipments") List<Equipment> equipments,@RequestBody List<Service> services) throws ParseException {
        CarDTO car = carService.getCarByBarcode(carBarcodeNum);

        if (!car.getStatus().equals("Available"))
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(null);

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
        try {
            Boolean isCarReturned = reservationService.returnCar(reservationNumber);

            if (!isCarReturned)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isCarReturned);

            else
                return ResponseEntity.status(HttpStatus.OK).body(isCarReturned);

        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).body(null);
        }
    }

    @PutMapping(value = "/cancelReservation/{reservationNumber}")
    @Operation(summary = "Cancel Reservation",description = "Updating reservation status as Cancelled")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Reservation Number Not found"),
            @ApiResponse(responseCode = "500", description = "Exception is thrown") })
    public ResponseEntity<Boolean> cancelReservation(@PathVariable("reservationNumber") String reservationNumber) {

        try {
            Boolean isCancelled = reservationService.cancelReservation(reservationNumber);

            if (isCancelled)
                return ResponseEntity.status(HttpStatus.OK).body(isCancelled);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isCancelled);


        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).body(null);
        }
    }

}
