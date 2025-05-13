package com.example.demo.domain.dto;

public class OrderDTO {
    private Long orderSlipNumber;
    private String orderDate;
    private String customerName;

    public OrderDTO(Long long1, String orderDate, String customerName) {
        this.orderSlipNumber = long1;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public Long getOrderSlipNumber() {
        return orderSlipNumber;
    }

    public void setOrderSlipNumber(Long orderSlipNumber) {
        this.orderSlipNumber = orderSlipNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}