package com.example.RentCar.Controller;

import com.example.RentCar.DTO.CarDTO;
import com.example.RentCar.DTO.RentedCarDTO;
import com.example.RentCar.Service.CarService;
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

@RestController()
@RequestMapping(value = "/Cars")
@CrossOrigin
public class CarController {

    @Autowired
    CarService carService;


    @GetMapping(value = "/{carType}/{transmissionType}")
    @Operation(summary = "Find all available cars", description = "Get all the available cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "There is no available car") })
    public ResponseEntity<List<CarDTO>> findAllAvailableCars(@PathVariable("carType") String carType, @PathVariable("transmissionType") String transmissionType) {
        List<CarDTO> dtoList = carService.findAvailableCars(carType,transmissionType);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping(value = "/getAllCars")
    @Operation(summary = "Find all cars", description = "Get all the cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "There is no car") })
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> dtoList = carService.getAllCars();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping(value = "/getRentedCars")
    @Operation(summary = "Get rented cars", description = "Get all the loaned and reserved cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RentedCarDTO.class))),
            @ApiResponse(responseCode = "404", description = "No reserved or rented cars") })
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars() {
        List<RentedCarDTO> dtoList = carService.getAllRentedCars();

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping(value = "/saveCar")
    @Operation(summary = "Save car", description = "Save car to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RentedCarDTO.class))),
            @ApiResponse(responseCode = "404", description = "No car is saved") })
    public  ResponseEntity<CarDTO> save(@RequestBody CarDTO dto) {
        CarDTO carDTO = carService.save(dto);

        return ResponseEntity.status(HttpStatus.OK).body(carDTO);
    }

}