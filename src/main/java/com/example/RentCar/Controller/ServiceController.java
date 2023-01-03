package com.example.RentCar.Controller;


import com.example.RentCar.DTO.ServiceDTO;
import com.example.RentCar.Service.ServiceService;
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
@RequestMapping(value = "/Services")
@CrossOrigin
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping(value = "/getServices")
    @Operation(summary = "Find all services", description = "Get all the services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ServiceDTO.class))),
            @ApiResponse(responseCode = "404", description = "There is no service") })
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> dtoList = serviceService.getAllServiceList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping(value = "/addServiceToReservation/{reservationNum}/{serviceCode}")
    @Operation(summary = "Add service to the reservation", description = "Add service to the reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ServiceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Equipment is Not found"),
            @ApiResponse(responseCode = "505", description = "exception thrown")})
    public ResponseEntity<Boolean> addAdditionalServicesToReservation(@PathVariable("reservationNum") String reservationNumber, @PathVariable("serviceCode") int serviceCode) {

        try {
            Boolean isAdded = serviceService.addAdditionalServiceToReservation(reservationNumber,serviceCode);

            if (!isAdded)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isAdded);

            return ResponseEntity.status(HttpStatus.OK).body(isAdded);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).body(null);
        }


    }
}
