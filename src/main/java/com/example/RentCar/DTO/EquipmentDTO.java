package com.example.RentCar.DTO;

public class EquipmentDTO {

    private Long id;
    private double fixedPrice;
    private String name;
    private int code;

    public EquipmentDTO() {

    }

    public EquipmentDTO(Long id, double fixedPrice, String name) {
        this.id = id;
        this.fixedPrice = fixedPrice;
        this.name = name;
    }

    public EquipmentDTO(double fixedPrice, String name, int code) {
        this.fixedPrice = fixedPrice;
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
