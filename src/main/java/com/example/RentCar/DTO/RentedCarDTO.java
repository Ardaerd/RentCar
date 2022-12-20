package com.example.RentCar.DTO;

import com.example.RentCar.Model.Location;

import java.util.Date;

public class RentedCarDTO {
    private String brand;
    private String model;
    private String carType;
    private String transmissionType;
    private String barcode;
    private String reservationNumber;
    private String memberName;
    private Date dropOffDate;
    private Location dropOffLocation;
    private int reservationDayCount;

    public RentedCarDTO(String brand, String model, String carType, String transmissionType, String barcode, String reservationNumber, String memberName, Date dropOffDate, Location dropOffLocation, int reservationDayCount) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.transmissionType = transmissionType;
        this.barcode = barcode;
        this.reservationNumber = reservationNumber;
        this.memberName = memberName;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.reservationDayCount = reservationDayCount;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public int getReservationDayCount() {
        return reservationDayCount;
    }

    public void setReservationDayCount(int reservationDayCount) {
        this.reservationDayCount = reservationDayCount;
    }
}
