package com.example.RentCar.DTO;

public class ServiceDTO {

    private Long id;
    private double fixedPrice;
    private String name;

    public ServiceDTO() {

    }

    public ServiceDTO(Long id, double fixedPrice, String name) {
        this.id = id;
        this.fixedPrice = fixedPrice;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
