package com.example.RentCar.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "barcode")
    private String barcode;
    @Column(name = "licensePlateNumber")
    private float licensePlateNumber;
    @Column(name = "passengerCapacity")
    private int passengerCapacity;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "transmissionType")
    private String transmissionType;
    @Column(name = "dailyPrice")
    private double dailyPrice;
    @Column(name = "carType")
    private String carType;
    @Column(name = "status")
    private String status;

    @Column(name = "mileage")
    private Long mileage;

    public Car() {
    }

    public Car(String barcode, float licensePlateNumber, int passengerCapacity, String brand, String model, String transmissionType, double dailyPrice, String carType, String status, Long mileage) {
        this.barcode = barcode;
        this.licensePlateNumber = licensePlateNumber;
        this.passengerCapacity = passengerCapacity;
        this.brand = brand;
        this.model = model;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.carType = carType;
        this.status = status;
        this.mileage = mileage;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public float getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(float licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
