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

        if (dtoList == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoList);

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping(value = "/getAllCars")
    @Operation(summary = "Find all cars", description = "Get all the cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "There is no car") })
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> dtoList = carService.getAllCars();

        if (dtoList == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoList);

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping(value = "/getRentedCars")
    @Operation(summary = "Get rented cars", description = "Get all the loaned and reserved cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RentedCarDTO.class))),
            @ApiResponse(responseCode = "404", description = "No reserved or rented cars") })
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars() {
        List<RentedCarDTO> dtoList = carService.getAllRentedCars();

        if (dtoList == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoList);

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }


    @DeleteMapping(value = "/deleteCar/{carBarcode}")
    @Operation(summary = "Delete Car",description = "Delete car from the db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "406", description = "Not acceptable"),
            @ApiResponse(responseCode = "500", description = "Exception is thrown") })
    public ResponseEntity<Boolean> deleteCar(@PathVariable("carBarcode") String carBarcodeNum) {

        try {

            Boolean isDeleted = carService.deleteCar(carBarcodeNum);
            CarDTO car = carService.getCarByBarcode(carBarcodeNum);

            if (!car.getStatus().equals("Available"))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);

            if (!isDeleted)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isDeleted);

            return ResponseEntity.status(HttpStatus.OK).body(isDeleted);

        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).body(null);
        }
    }


    @PostMapping(value = "/saveCar")
    @Operation(summary = "Save car", description = "Save car to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "No car is saved") })
    public  ResponseEntity<CarDTO> save(@RequestBody CarDTO dto) {
        CarDTO carDTO = carService.save(dto);

        if (carDTO == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(carDTO);


        return ResponseEntity.status(HttpStatus.OK).body(carDTO);
    }

}
