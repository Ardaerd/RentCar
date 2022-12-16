package com.example.RentCar.DTO;

public class LocationDTO {

    private Long id;
    private int code;
    private String address;

    public LocationDTO() {

    }

    public LocationDTO(int code, String address) {
        this.code = code;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
