package com.example.RentCar.DTO;

public class MemberDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private float drivingLicenseNumber;

    public MemberDTO() {
        
    }

    public MemberDTO(String name, String phoneNumber, String email, String address, float drivingLicenseNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(float drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
