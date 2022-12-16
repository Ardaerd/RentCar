package com.example.RentCar.DTO;

import com.example.RentCar.Model.Location;
import com.example.RentCar.Model.Member;

import java.util.Date;

public class ReservationDTO {

    private Long id;

    private float reservationNumber;
    private Date creationDate;
    private Date pickUpDate;
    private Date dropOffDate;
    private Location dropOffLocation;
    private Date returnDate;
    private String statu;
    private Member member;


    public ReservationDTO() {

    }

    public ReservationDTO(Long id, float reservationNumber, Date creationDate, Date pickUpDate, Date dropOffDate, Location dropOffLocation, Date returnDate, String statu, Member member) {
        this.id = id;
        this.reservationNumber = reservationNumber;
        this.creationDate = creationDate;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.returnDate = returnDate;
        this.statu = statu;
        this.member = member;
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

    public float getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(float reservationNumber) {
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
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }
}
