package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "merchandise")
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchandise_number")
    private Long merchandiseNumber;

    @Column(name = "merchandise_name")
    private String merchandiseName;

    @Column(name = "unit_price")
    private Double unitPrice;
    public Long getMerchandiseNumber() {
        return merchandiseNumber;
    }

    public void setMerchandiseNumber(Long merchandiseNumber) {
        this.merchandiseNumber = merchandiseNumber;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}