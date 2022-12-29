package com.example.RentCar.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String reservationNumber;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date pickUpDate;
    @Temporal(TemporalType.DATE)
    private Date dropOffDate;

    @ManyToOne
    @JoinColumn(name = "drop_off_location_id")
    private Location dropOffLocation;

    @ManyToOne
    @JoinColumn(name = "pick_up_location_id")
    private Location pickUpLocation;

    @Temporal(TemporalType.DATE)
    private Date returnDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
    private List<Service> services;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
    private List<Equipment> equipments;

    public Reservation() {
    }

    public Reservation(String reservationNumber, Car car, Date creationDate, Date pickUpDate, Date dropOffDate, Location dropOffLocation, Location pickUpLocation, Date returnDate, String status, Member member) {
        this.reservationNumber = reservationNumber;
        this.car = car;
        this.creationDate = creationDate;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLocation = pickUpLocation;
        this.returnDate = returnDate;
        this.status = status;
        this.member = member;
    }

    public Reservation(String reservationNumber, Car car, Date pickUpDate, Date dropOffDate, Location dropOffLocation, Location pickUpLocation, Date returnDate, String status, Member member, List<Service> services, List<Equipment> equipments) throws ParseException {
        this.reservationNumber = reservationNumber;
        this.car = car;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLocation = pickUpLocation;
        this.returnDate = returnDate;
        this.status = status;
        this.member = member;
        this.equipments = equipments;
        this.services = services;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.creationDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(formatter.format(date));
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
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
}
