package com.example.demo.domain.dto;

public class MerchandiseDTO {
    private Long merchandiseNumber;
    private String merchandiseName;
    private Double unitPrice;

    public MerchandiseDTO(Long long1, String merchandiseName, Double unitPrice) {
        this.merchandiseNumber = long1;
        this.merchandiseName = merchandiseName;
        this.unitPrice = unitPrice;
    }

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