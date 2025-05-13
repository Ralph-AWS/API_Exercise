package com.example.demo.domain.dto;

public class OrderDetailDTO {
    private Long no;
    private OrderDTO order;
    private MerchandiseDTO merchandise;
    private int quantity;

    public OrderDetailDTO(Long long1, OrderDTO order, MerchandiseDTO merchandise, int quantity) {
        this.no = long1;
        this.order = order;
        this.merchandise = merchandise;
        this.quantity = quantity;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public MerchandiseDTO getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(MerchandiseDTO merchandise) {
        this.merchandise = merchandise;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}