package com.example.RentCar.DTO;

import com.example.RentCar.Model.Location;
import com.example.RentCar.Model.Member;

import java.util.Date;

public class ReservationDTO {

    private String reservationNumber;
    private Date pickUpDate;
    private Date dropOffDate;
    private Location dropOffLocation;
    private Location pickUpLocation;
    private double amount;


    public ReservationDTO() {

    }

    public ReservationDTO(String reservationNumber, Date pickUpDate, Date dropOffDate, Location dropOffLocation, Location pickUpLocation, Member member, double amount) {
        this.reservationNumber = reservationNumber;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLocation = pickUpLocation;
        this.amount = amount;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
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

}
