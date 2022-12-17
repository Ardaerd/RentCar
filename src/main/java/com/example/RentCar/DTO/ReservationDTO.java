package com.example.RentCar.DTO;

import com.example.RentCar.Model.Location;
import com.example.RentCar.Model.Member;

import java.util.Date;

public class ReservationDTO {

    private Long id;
    private String reservationNumber;
    private Date creationDate;
    private Date pickUpDate;
    private Date dropOffDate;
    private Location dropOffLocation;
    private Location pickUpLocation;
    private Date returnDate;
    private String status;
    private Member member;
    private double amount;


    public ReservationDTO() {

    }

    public ReservationDTO(String reservationNumber, Date pickUpDate, Date dropOffDate, Location dropOffLocation, Location pickUpLocation, Member member, double amount) {
        this.reservationNumber = reservationNumber;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLocation = pickUpLocation;
        this.member = member;
        this.amount = amount;
    }

    public ReservationDTO(Long id, Location pickUpLocation, double amount, String reservationNumber, Date creationDate, Date pickUpDate, Date dropOffDate, Location dropOffLocation, Date returnDate, String status, Member member) {
        this.id = id;
        this.reservationNumber = reservationNumber;
        this.amount = amount;
        this.creationDate = creationDate;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLocation = pickUpLocation;
        this.returnDate = returnDate;
        this.status = status;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatu() {
        return status;
    }

    public void setStatu(String status) {
        this.status = status;
    }
}
