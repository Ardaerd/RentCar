package com.example.RentCar.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private double fixedPrice;
    private String name;

    public Equipment() {
    }

    public Equipment(double fixedPrice, String name) {
        this.fixedPrice = fixedPrice;
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
